package ir.abyx.daneshjooyar.mvp.model

import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.data.local.dataModel.CatModel
import ir.abyx.daneshjooyar.data.local.dataModel.ContentModel
import ir.abyx.daneshjooyar.data.local.dataModel.MainModel
import ir.abyx.daneshjooyar.data.local.dataModel.VideoModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.mvp.ext.ToastUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelHomeFragment {

    private val catTitles =
        listOf("طراحی سایت", "برنامه نویسی موبایل", "هوش مصنوعی", "امنیت شبکه", "بازی سازی")
    private val catIcon =
        listOf(
            R.drawable.ic_site, R.drawable.ic_mobile_development,
            R.drawable.ic_ai, R.drawable.ic_site, R.drawable.ic_ai
        )
    private val contentTitle1 = listOf(
        "آموزش راه اندازی کسب و کار اینترنتی (رایگان و کاربردی)",
        "دوره آموزش المنتور رایگان به همراه ۲ پروژه عملی",
        "دوره آموزش امنیت سایت به صورت رایگاه",
        "دوره آموزش سئو سایت رایگان همراه با پروژه عملی"
    )
    private val contentImage1 = listOf(
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3,
        R.drawable.image_1,
    )

    private val contentTitle2 = listOf(
        "دوره آموزش برنامه نویسی کاتلین (مقدماتی)",
        "دوره آموزش برنامه نویسی کاتلین (پیشرفته)",
        "دوره آموزش برنامه نویسی اندروید به زبان کاتلین به همراه پروژه عملی",
        "دوره آموزش برنامه نویسی فلاتر",
    )
    private val contentImage2 = listOf(
        R.drawable.image_3,
        R.drawable.image_2,
        R.drawable.image_1,
        R.drawable.image_3,
    )

    fun getMain(callBackRequest: CallbackRequest<MainModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val cats = ArrayList<CatModel>()
            val contents = ArrayList<ContentModel>()

            for (i in catTitles.indices)
                cats.add(CatModel(id = i, title = catTitles[i], icon = catIcon[i]))


            for (i in contentTitle1.indices)
                contents.add(
                    ContentModel(
                        id = i,
                        title = contentTitle1[i],
                        image = contentImage1[i]
                    )
                )

            val mainModel = MainModel(cats, contents)
            callBackRequest.getResponse(arrayListOf(mainModel))
        }
    }

//    fun getCats(callBackRequest: CallbackRequest<CatModel>) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val myCats = ArrayList<CatModel>()
//            for (i in 0..3)
//                myCats.add(CatModel(id = i, title = catTitles[i], icon = catIcon[i]))
//
//            callBackRequest.getResponse(myCats)
//        }
//    }

    fun getContentById(id: Int, callBackRequest: CallbackRequest<ContentModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val myContents = ArrayList<ContentModel>()
            val finalContentTitle: List<String>
            val finalContentImage: List<Int>

            when (id) {
                0 -> {
                    finalContentTitle = contentTitle1
                    finalContentImage = contentImage1
                }

                1 -> {
                    finalContentTitle = contentTitle2
                    finalContentImage = contentImage2
                }

                2 -> {
                    finalContentTitle = contentTitle1
                    finalContentImage = contentImage1
                }

                3 -> {
                    finalContentTitle = contentTitle2
                    finalContentImage = contentImage2
                }

                4 -> {
                    finalContentTitle = contentTitle1
                    finalContentImage = contentImage1
                }

                else -> {
                    finalContentTitle = contentTitle1
                    finalContentImage = contentImage1
                }
            }

            for (i in finalContentTitle.indices)
                myContents.add(
                    ContentModel(
                        id = i,
                        title = finalContentTitle[i],
                        image = finalContentImage[i]
                    )
                )

            callBackRequest.getResponse(myContents)
        }
    }
}