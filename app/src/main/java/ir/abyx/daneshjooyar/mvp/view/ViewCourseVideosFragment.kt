package ir.abyx.daneshjooyar.mvp.view

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.abyx.daneshjooyar.adapter.recycler.RecyclerAdapterVideo
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.data.local.dataModel.CourseModel
import ir.abyx.daneshjooyar.data.local.dataModel.CourseVideosModel
import ir.abyx.daneshjooyar.databinding.FragmentCourseVideosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewCourseVideosFragment(context: Context, private val activityUtils: ActivityUtils) {

    val binding = FragmentCourseVideosBinding.inflate(LayoutInflater.from(context))

    fun initialize(courseVideos: CourseModel) {
        binding.apply {
            recyclerVideos.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

                val final = courseVideos.courseVideo
                final.add(0, CourseVideosModel(0, "", 0))
                CoroutineScope(Dispatchers.Main).launch {

                    courseVideos.courseVideoState.collect {
                        adapter = RecyclerAdapterVideo(
                            context,
                            courseVideos.title,
                            final,
                            it.toArrayList()
                        )
                    }
                }
            }
        }
    }

    private fun <T> List<T>.toArrayList(): ArrayList<T> {
        return ArrayList(this)
    }
}