package ir.abyx.daneshjooyar.mvp.view

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import ir.abyx.daneshjooyar.adapter.recycler.RecyclerAdapterGrid
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.data.local.dataModel.CourseInfoModel
import ir.abyx.daneshjooyar.databinding.FragmentCourseInfoBinding

class ViewCourseInfoFragment(context: Context, activityUtils: ActivityUtils) {

    val binding = FragmentCourseInfoBinding.inflate(LayoutInflater.from(context))

    fun initialize(data: CourseInfoModel) {
        binding.apply {
            imgCourse.setImageResource(data.image)

            recyclerViewPanel.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = RecyclerAdapterGrid(data.panelInfo)
            }
        }
    }

}