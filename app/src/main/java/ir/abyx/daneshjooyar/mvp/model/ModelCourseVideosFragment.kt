package ir.abyx.daneshjooyar.mvp.model

import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.data.local.dataModel.CourseVideosModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelCourseVideosFragment {


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

    fun getVideoList(callbackRequest: CallbackRequest<CourseVideosModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val courseVideos = ArrayList<CourseVideosModel>()

            for (i in videoTitles.indices) {
                courseVideos.add(CourseVideosModel(i, videoTitles[i], videoImages[i], 0))
            }

            callbackRequest.getResponse(courseVideos)
        }
    }
}