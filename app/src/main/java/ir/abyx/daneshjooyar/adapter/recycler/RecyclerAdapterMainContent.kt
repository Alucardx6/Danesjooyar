package ir.abyx.daneshjooyar.adapter.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ir.abyx.daneshjooyar.data.local.dataModel.ContentModel
import ir.abyx.daneshjooyar.databinding.RecyclerItemListContentBinding
import ir.abyx.daneshjooyar.ui.activity.CourseDetailActivity

class RecyclerAdapterMainContent(
    private val context: Context,
    private val contents: ArrayList<ContentModel>
) : RecyclerView.Adapter<RecyclerAdapterMainContent.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
        CustomViewHolder(
            RecyclerItemListContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = contents.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.setData(contents[position])

    inner class CustomViewHolder(val binding: RecyclerItemListContentBinding) :
        ViewHolder(binding.root) {
        fun setData(content: ContentModel) {
            binding.apply {
                txtTitle.text = content.title
                imgCourse.setImageResource(content.image)

                root.setOnClickListener {
                    val intent = Intent(context, CourseDetailActivity::class.java)
                    intent.putExtra("title", content.title)
                    intent.putExtra("id", content.id)
                    context.startActivity(intent)
                }
            }
        }
    }
}
