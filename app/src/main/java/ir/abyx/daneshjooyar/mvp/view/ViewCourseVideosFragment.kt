package ir.abyx.daneshjooyar.mvp.view

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.abyx.daneshjooyar.adapter.recycler.RecyclerAdapterVideo
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.data.local.dataModel.CourseVideosModel
import ir.abyx.daneshjooyar.databinding.FragmentCourseVideosBinding

class ViewCourseVideosFragment(context: Context, private val activityUtils: ActivityUtils) {

    val binding = FragmentCourseVideosBinding.inflate(LayoutInflater.from(context))

    fun initialize(courseVideos: ArrayList<CourseVideosModel>) {
        binding.apply {
            recyclerVideos.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = RecyclerAdapterVideo(context, courseVideos)
            }
        }
    }

}