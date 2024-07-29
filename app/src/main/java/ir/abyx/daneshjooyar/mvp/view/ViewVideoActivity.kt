package ir.abyx.daneshjooyar.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.data.local.dataModel.VideoModel
import ir.abyx.daneshjooyar.databinding.ActivityVideoBinding
import ir.abyx.daneshjooyar.mvp.ext.ViewUtils
import kotlin.math.roundToInt

class ViewVideoActivity(private val context: Context, private val activityUtils: ActivityUtils) {

    private lateinit var player: SimpleExoPlayer
    private var isFullscreen = false

    var startPosition: Long = 0L
    private var endPosition: Long? = null

    val binding = ActivityVideoBinding.inflate(LayoutInflater.from(context))

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    fun initialize(videoInfo: String, history: VideoModel, viewUtils: ViewUtils) {
        binding.apply {

            txtVideoTitle.text = history.title

            progressBar.progress = history.percent.roundToInt()
            txtProgress.text = "${progressBar.progress}/100%"
            updateLottiePosition()

            //region videoPlayer
            val btnFullscreen = videoPlayer.findViewById<ImageView>(R.id.img_fullscreen)

            player = SimpleExoPlayer.Builder(context)
                .setSeekBackIncrementMs(5000)
                .setSeekForwardIncrementMs(5000)
                .build()
            videoPlayer.player = player
            videoPlayer.keepScreenOn = true

            val mediaItem = MediaItem.fromUri(videoInfo)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()


            player.addListener(object : Player.Listener {
                //this code run when video pause or play state change
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    if (history.percent < 100) {
                        if (isPlaying) {
                            //set start position equal to video current time playing
                            startPosition = videoCurrentTime()

                            viewUtils.startPolling(startPosition)
                        } else {
                            if (endPosition != null) {
                                viewUtils.videoStop(endPosition, startPosition, videoCurrentTime())
                            } else {
                                viewUtils.videoStop(null, startPosition, videoCurrentTime())
                            }
                            endPosition = null
                        }
                    }
                }

                //this code run when user seek
                override fun onPositionDiscontinuity(
                    oldPosition: Player.PositionInfo,
                    newPosition: Player.PositionInfo,
                    reason: Int
                ) {
                    when (reason) {
                        Player.DISCONTINUITY_REASON_SEEK -> {

                            //when user seek from point a to point b, point end position set to a for closing the timeline
                            val oldPositionSeconds = oldPosition.positionMs

                            endPosition = oldPositionSeconds

                            Log.i("DEBUG_TIMELINE", "end: $endPosition")
                        }

                        else -> {}
                    }
                }
            })

            btnFullscreen.setOnClickListener {
                isFullscreen = !isFullscreen
                if (isFullscreen) {
                    btnFullscreen.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_exit_fullscreen
                        )
                    )
                } else {
                    btnFullscreen.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_fullscreen
                        )
                    )
                }
                activityUtils.fullScreen(isFullscreen)
            }
            //endregion

            //region progressBar

            progressBar.setOnTouchListener { _: View?, _: MotionEvent? -> true }

            progressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
//                    val thumbX = seekBar?.thumb?.bounds?.left ?: 0
//                    val newX = (thumbX - lottieAnimationView.width / 2 + 16).toFloat()
//                    lottieAnimationView.x = maxOf(0f, newX)
                    updateLottiePosition()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            //endregion
        }
    }

    fun getVideoDuration() = player.duration


    fun updateProgress(percent: Float) {
        Log.i("DEBUG_TIMELINE", videoCurrentTime().toString())

        binding.progressBar.progress = percent.roundToInt()
        binding.txtProgress.text = "${binding.progressBar.progress}/100%"
    }

    fun videoCurrentTime(): Long = player.currentPosition

    fun backButton() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            activityUtils.finished()
        }
    }

    private fun updateLottiePosition() {
        val thumbX = binding.progressBar.thumb.bounds.left
        val newX = (thumbX - binding.lottieAnimationView.width / 2 + 32).toFloat()
        binding.lottieAnimationView.x = maxOf(0f, newX)
    }

    fun stopPlayer() {
        player.stop()
    }

    fun releasePlayer() {
        player.release()
    }

}