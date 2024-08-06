package ir.abyx.daneshjooyar.data.local.dataModel

import kotlinx.coroutines.flow.Flow

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
    val id: Int = 1,
    val title: String,
    val image: Int
)

data class CourseModel(
    val title: String,
    val courseVideo: ArrayList<CourseVideosModel>,
    val courseVideoState: Flow<List<VideoModel>>
)