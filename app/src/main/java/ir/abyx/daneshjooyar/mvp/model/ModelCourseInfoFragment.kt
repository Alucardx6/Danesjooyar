package ir.abyx.daneshjooyar.mvp.model

import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.data.local.dataModel.CourseInfoModel
import ir.abyx.daneshjooyar.data.local.dataModel.PanelInfoModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.mvp.ext.OthersUtilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelCourseInfoFragment {

    private val image = listOf(
        R.drawable.teacher
    )

    private val panelIcon =
        listOf(
            R.drawable.students, R.drawable.rates,
            R.drawable.counts, R.drawable.hours
        )
    private val panelTitles =
        listOf(
            "${OthersUtilities.splitNumber(68666)} نفر",
            "${4.5} از ۵",
            "${28} عدد",
            "${785} ساعت"
        )

    private val panelDesc = listOf(
        "تعداد دانشجو",
        "امتیاز دانشجویان",
        "تعداد دوره ها",
        "ساعت آموزش",
    )

    fun getCourseInfo(callbackRequest: CallbackRequest<CourseInfoModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val panelInfo = ArrayList<PanelInfoModel>()

            for (i in panelIcon.indices) {
                panelInfo.add(PanelInfoModel(panelIcon[i], panelTitles[i], panelDesc[i]))
            }

            callbackRequest.getResponse(arrayListOf(CourseInfoModel(0, image[0], panelInfo)))
        }
    }

}