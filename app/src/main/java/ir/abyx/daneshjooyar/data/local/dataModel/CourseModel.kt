package ir.abyx.daneshjooyar.data.local.dataModel

data class CourseInfoModel(
    val id: Int = 0,
    val image: Int,
    val panelInfo: ArrayList<PanelInfoModel>
)

data class PanelInfoModel(
    val icon: Int,
    val title: String,
    val desc: String,
)

data class CourseVideosModel(
    val id: Int = 0,
    val title: String,
    val image: Int,
    val progress: Int
)