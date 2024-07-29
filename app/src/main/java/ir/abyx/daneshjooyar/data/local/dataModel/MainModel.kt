package ir.abyx.daneshjooyar.data.local.dataModel

data class MainModel(
    val cats: ArrayList<CatModel>,
    val contents: ArrayList<ContentModel>
)

data class CatModel(
    val id: Int = 0,
    val title: String,
    val icon: Int
)

data class ContentModel(
    val id: Int = 0,
    val title: String,
    val image: Int
)

//data class VideoModel(
//    val history: MutableList<Pair<Long, Long>>,
//    val percent: Float
//)