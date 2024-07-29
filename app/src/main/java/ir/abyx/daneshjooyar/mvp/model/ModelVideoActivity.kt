package ir.abyx.daneshjooyar.mvp.model

import android.annotation.SuppressLint
import android.content.Context
import ir.abyx.daneshjooyar.data.local.dataModel.VideoModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.mvp.ext.ToastUtils
import ir.abyx.pastry.data.local.preferences.SecureSharePref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModelVideoActivity(private val id: Int, private val context: Context) {
    fun getVideoHistory(callBackRequest: CallbackRequest<VideoModel>) {
        CoroutineScope(Dispatchers.IO).launch {

            val history = SecureSharePref.getSharedPref(context).getString("video$id", null)

            val final: MutableList<Pair<Long, Long>> = try {
                history?.split("|")?.map {
                    val (start, end) = it.split(",").map { it.toLong() }
                    Pair(start, end)
                }?.toMutableList() ?: mutableListOf()
            } catch (e: Exception) {
                mutableListOf()
            }

            val percent = SecureSharePref.getSharedPref(context).getFloat("percent$id", 1f)

            callBackRequest.getRes(VideoModel(watchedHistory = final, percent = percent))
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun setVideoHistory(
        watchedSegments: MutableList<Pair<Long, Long>>,
        percent: Float
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val watchedSegmentsString =
                watchedSegments.joinToString(separator = "|") { "${it.first},${it.second}" }

            val test = SecureSharePref.getSharedPref(context).edit()
                .putString("video$id", watchedSegmentsString)
                .putFloat("percent$id", percent)
                .commit()

            withContext(Dispatchers.Main) {
                if (test) {
                    ToastUtils.toast(context, "اطلاعات با موفقیت ثبت شد")
                } else {
                    ToastUtils.toast(context, "خطلا")
                }
            }
        }
    }

}