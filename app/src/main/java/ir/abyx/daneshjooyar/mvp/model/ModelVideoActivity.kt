package ir.abyx.daneshjooyar.mvp.model

import android.content.Context
import ir.abyx.daneshjooyar.data.local.dataModel.VideoModel
import ir.abyx.daneshjooyar.data.local.db.DBHelper
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelVideoActivity(
    private val id: Int,
    private val title: String,
    private val context: Context
) {

    private val service = DBHelper.getDatabase(context).videoDao()

    fun getVideoHistory(callBackRequest: CallbackRequest<VideoModel>) {
        CoroutineScope(Dispatchers.IO).launch {

//            val history = SecureSharePref.getSharedPref(context).getString("video$id", null)
//

            val result = service.getVideoHistoryById(id)

//            val final: MutableList<Pair<Long, Long>> = try {
//                result.watchedHistory.split("|").map {
//                    val (start, end) = it.split(",").map { it.toLong() }
//                    Pair(start, end)
//                }.toMutableList() ?: mutableListOf()
//            } catch (e: Exception) {
//                mutableListOf()
//            }


            if (result != null) {
                callBackRequest.getRes(result)
            } else {
                callBackRequest.getRes(VideoModel(watchedHistory = "", percent = 1f))
            }

            //val percent = SecureSharePref.getSharedPref(context).getFloat("percent$id", 1f)


        }
    }

    fun setVideoHistory(
        watchedSegments: MutableList<Pair<Long, Long>>,
        percent: Float
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val watchedSegmentsString =
                watchedSegments.joinToString(separator = "|") { "${it.first},${it.second}" }

            val check = service.getVideoHistoryById(id)

            if (check != null)
                service.updateVideoHistory(VideoModel(id, watchedSegmentsString, percent))
            else {
                service.saveVideoHistory(
                    VideoModel(
                        id = id,
                        watchedHistory = watchedSegmentsString,
                        percent = percent,
                    )
                )
            }


//            val watchedSegmentsString =
//                watchedSegments.joinToString(separator = "|") { "${it.first},${it.second}" }
//
//            val test = SecureSharePref.getSharedPref(context).edit()
//                .putString("video$id", watchedSegmentsString)
//                .putFloat("percent$id", percent)
//                .commit()

        }
    }

}