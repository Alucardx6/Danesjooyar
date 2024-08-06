package ir.abyx.daneshjooyar.mvp.model

import android.content.Context
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.data.local.dataModel.CourseModel
import ir.abyx.daneshjooyar.data.local.dataModel.CourseVideosModel
import ir.abyx.daneshjooyar.data.local.dataModel.VideoModel
import ir.abyx.daneshjooyar.data.local.db.DBHelper
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelCourseVideosFragment(context: Context, private val title: String) {

    private val service = DBHelper.getDatabase(context).videoDao()


    private val videoTitles = listOf(
        "۱. المنتور چیست؟ چرا باید از آن استفاده کنیم؟",
        "۲.نصب و راه اندازی المنتور",
        "۳. افزایش محدوده حافظه Php در وردپرس",
        "۴. بررسی کامل المنتور رایگان و قابلیت های آن",
        "۵. نکات حیاتی در استفاده از افزونه های غیر رایگان"
    )

    private val videoImages = listOf(
        R.drawable.image_2,
        R.drawable.image_2,
        R.drawable.image_2,
        R.drawable.image_2,
        R.drawable.image_2,
    )

    fun getVideoList(callbackRequest: CallbackRequest<CourseModel>) {
        CoroutineScope(Dispatchers.IO).launch {

            val courseVideos = ArrayList<CourseVideosModel>()

            for (i in videoTitles.indices) {
                if (service.getVideoHistoryById(i + 1) == null) {
                    service.saveVideoHistory(VideoModel(i + 1))
                }

                courseVideos.add(CourseVideosModel(i + 1, videoTitles[i], videoImages[i]))
            }

            val videoState = service.getVideoHistory

            callbackRequest.getRes(CourseModel(title, courseVideos, videoState))
        }
    }
}