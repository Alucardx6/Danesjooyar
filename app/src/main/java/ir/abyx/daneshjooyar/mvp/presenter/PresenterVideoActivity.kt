package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.data.local.dataModel.VideoModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.ext.ViewUtils
import ir.abyx.daneshjooyar.mvp.model.ModelVideoActivity
import ir.abyx.daneshjooyar.mvp.view.ViewVideoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PresenterVideoActivity(
    private val context: Context, private val view: ViewVideoActivity,
    private val model: ModelVideoActivity
) : BaseLifecycle {

    private var positionPollingRunnable: Runnable? = null
    private var positionPollingHandler: Handler? = null

    private var watchedSegments = mutableListOf<Pair<Long, Long>>()

    private var recordingSection: Boolean = false

    private var actualPercent: Float = 1f
    private var defaultPercent: Float = 0f

    override fun onCreate() {
        view.initAppBar()
        initialize()
    }

    private fun initialize() {
        val videoFilePath = "android.resource://${context.packageName}/${R.raw.hitler}"

        model.getVideoHistory(object : CallbackRequest<VideoModel> {
            override fun getRes(response: VideoModel) {

                Log.i("DEBUG_TIMELINE", "sqlite: $response")

                val final: MutableList<Pair<Long, Long>> = try {
                    response.watchedHistory.split("|").map {
                        val (start, end) = it.split(",").map { it.toLong() }
                        Pair(start, end)
                    }.toMutableList()
                } catch (e: Exception) {
                    mutableListOf()
                }

                watchedSegments = final
                actualPercent = response.percent

                CoroutineScope(Dispatchers.Main).launch {
                    view.initialize(videoFilePath, response, object : ViewUtils {
                        override fun startPolling(startPosition: Long) {

                            if (defaultPercent == 0f) {
                                defaultPercent =
                                    "%.2f".format((100f / view.getVideoDuration() * 1000f))
                                        .toFloat()
                            }

                            startPositionPolling(startPosition)
                        }

                        override fun videoStop(
                            stopPosition: Long?,
                            startPosition: Long,
                            currentTime: Long
                        ) {
                            CoroutineScope(Dispatchers.IO).launch {
                                stopPositionPolling()
                                var stopPos = stopPosition
                                if (stopPos == null || stopPos < startPosition)
                                    stopPos = currentTime
                                else
                                //cant remember this shit
                                    watchedSegments.forEach {
                                        if (stopPosition == it.second) {
                                            stopPos = currentTime
                                            return@forEach
                                        }
                                    }

                                // when player pause or finish or user seek and check save state(save is for checking if timeline is creating or not)
                                if (recordingSection) {
                                    //save start point of video and the point that video paused
                                    saveWatchedSegment(startPosition, stopPos!!)
                                    recordingSection = false
                                }

                                Log.i("DEBUG_TIMELINE", "end position: $stopPos")
                            }
                        }
                    })
                }
            }
        })
    }

    private fun startPositionPolling(startPosition: Long) {
        //this code run every second when video is playing
        CoroutineScope(Dispatchers.IO).launch {
            var startPos = startPosition
            positionPollingHandler = Handler(Looper.getMainLooper())
            positionPollingRunnable = Runnable {

                val current = view.videoCurrentTime()
                if (recordingSection)
                    CoroutineScope(Dispatchers.Main).launch {
                        actualPercent += defaultPercent
                        view.updateProgress(actualPercent)
                    }

                //when watched segment is empty or null that means user watching video for the first time and dont need to run the check code which is else part
                if (watchedSegments.isNullOrEmpty())
                    recordingSection = true
                else
                //the reason for checking save is if loop runs every second it keep updating start position and time line dont save correctly, example:
                //if watchedSgments contains (0-10) and user seek to 15 save chenges to true and start position set to 15, next second start position set to 16 and...
                    if (!recordingSection) {

                        if (looper(current)) {
                            recordingSection = true
                            startPos = current
                            Log.i("DEBUG_TIMELINE", "startPos Changed to: $startPos")
                        } else {
                            recordingSection = false
                        }
                    } else {

                        if (!looper(current)) {
                            recordingSection = false
                            saveWatchedSegment(startPos, current)
                        }
                    }

                view.startPosition = startPos

                positionPollingHandler?.postDelayed(positionPollingRunnable!!, 1000L)
            }
            positionPollingHandler?.post(positionPollingRunnable!!)
        }
    }

    private fun looper(currentTime: Long): Boolean {
        return watchedSegments.none { segment ->
            currentTime.let { currentPosition ->
                currentPosition >= segment.first && currentPosition < segment.second
            }
        }
    }

    private fun stopPositionPolling() {
        positionPollingRunnable?.let {
            positionPollingHandler?.removeCallbacks(it)
        }
        positionPollingHandler = null
        positionPollingRunnable = null
    }

    private fun saveWatchedSegment(start: Long, end: Long) {
        if (!watchedSegments.contains(Pair(start, end)))
            watchedSegments.add(Pair(start, end))
        logWatchedSegments()
    }

    private fun logWatchedSegments() {
        for ((start, end) in watchedSegments) {
            Log.i("DEBUG_TIMELINE", "Watched segment: $start - $end")
        }
    }

    override fun onStop() {
        view.stopPlayer()
    }

    override fun onDestroy() {
        view.releasePlayer()
        model.setVideoHistory(watchedSegments, actualPercent)
    }
}