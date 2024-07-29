package ir.abyx.daneshjooyar.mvp.view

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.abyx.daneshjooyar.adapter.recycler.RecyclerAdapterCat
import ir.abyx.daneshjooyar.adapter.recycler.RecyclerAdapterMainContent
import ir.abyx.daneshjooyar.data.local.dataModel.ContentModel
import ir.abyx.daneshjooyar.data.local.dataModel.MainModel
import ir.abyx.daneshjooyar.databinding.FragmentHomeBinding
import ir.abyx.daneshjooyar.mvp.ext.ViewUtils

class ViewHomeFragment(
    context: Context
) {

    val binding = FragmentHomeBinding.inflate(LayoutInflater.from(context))

    fun initialize(data: MainModel, viewUtils: ViewUtils) {
        binding.apply {
            recyclerViewCat.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
                adapter = RecyclerAdapterCat(context, data.cats, viewUtils)
            }

            setContent(data.contents)
        }
    }

    fun setContent(data: ArrayList<ContentModel>) {
        binding.apply {
            recyclerViewContent.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = RecyclerAdapterMainContent(context, data)
            }
        }
    }

}