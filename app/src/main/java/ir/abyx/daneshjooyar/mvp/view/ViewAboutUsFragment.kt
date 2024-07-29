package ir.abyx.daneshjooyar.mvp.view

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import ir.abyx.daneshjooyar.adapter.recycler.RecyclerAdapterAboutUs
import ir.abyx.daneshjooyar.adapter.recycler.RecyclerAdapterGrid
import ir.abyx.daneshjooyar.adapter.recycler.ext.GridItemDecoration
import ir.abyx.daneshjooyar.data.local.dataModel.AboutModel
import ir.abyx.daneshjooyar.data.local.dataModel.CourseInfoModel
import ir.abyx.daneshjooyar.databinding.FragmentAboutUsBinding

class ViewAboutUsFragment(
    context: Context
) {

    val binding = FragmentAboutUsBinding.inflate(LayoutInflater.from(context))

    fun initialize(data: AboutModel) {
        binding.apply {

            recyclerViewGrid.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = RecyclerAdapterGrid(data.panelInfo)
            }

            recyclerViewGridFeatures.apply {
                layoutManager = GridLayoutManager(context, 2)
                addItemDecoration(GridItemDecoration(5))
                adapter = RecyclerAdapterAboutUs(data.features)
            }
        }
    }

}