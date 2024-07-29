package ir.abyx.daneshjooyar.mvp.model

import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.data.local.dataModel.AboutModel
import ir.abyx.daneshjooyar.data.local.dataModel.FeaturesModel
import ir.abyx.daneshjooyar.data.local.dataModel.PanelInfoModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.mvp.ext.OthersUtilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelAboutUsFragment {

    private val featuresIcons =
        listOf(
            R.drawable.features_1, R.drawable.img_features_2,
            R.drawable.img_features_3, R.drawable.img_features_4
        )

    private val featuresTitles =
        listOf(
            "مدرسین مجرب",
            "مدرک دو زبانه",
            "خیریه آموزشی",
            "دوره\u200Cهای تخصص محور"
        )

    private val panelIcon =
        listOf(
            R.drawable.likes, R.drawable.hat,
            R.drawable.counts, R.drawable.students
        )
    private val panelTitles =
        listOf(
            "+${OthersUtilities.splitNumber(25000)}",
            "+${OthersUtilities.splitNumber(400000)}",
            "+${2000}",
            "+${OthersUtilities.splitNumber(500)}"
        )

    private val panelDesc = listOf(
        "بازدید روزانه",
        "تعداد کاربران",
        "تعداد دوره ها",
        "تعداد مدرسین",
    )

    fun getCourseInfo(callbackRequest: CallbackRequest<AboutModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            val features = ArrayList<FeaturesModel>()
            val panelInfo = ArrayList<PanelInfoModel>()

            for (i in panelIcon.indices) {
                features.add(FeaturesModel(featuresIcons[i], featuresTitles[i]))
                panelInfo.add(PanelInfoModel(panelIcon[i], panelTitles[i], panelDesc[i]))
            }

            callbackRequest.getResponse(arrayListOf(AboutModel(0, panelInfo, features)))
        }
    }

}