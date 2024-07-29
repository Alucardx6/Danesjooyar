package ir.abyx.daneshjooyar.adapter.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ir.abyx.daneshjooyar.data.local.dataModel.CourseVideosModel
import ir.abyx.daneshjooyar.databinding.RecyclerItemListVideosBinding
import ir.abyx.daneshjooyar.ui.activity.VideoActivity

class RecyclerAdapterVideo(
    private val context: Context,
    private val videos: ArrayList<CourseVideosModel>
) :
    RecyclerView.Adapter<RecyclerAdapterVideo.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
        CustomViewHolder(
            RecyclerItemListVideosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.setData(videos[position])

    inner class CustomViewHolder(val binding: RecyclerItemListVideosBinding) :
        ViewHolder(binding.root) {
        fun setData(video: CourseVideosModel) {
            binding.apply {

                txtTitle.text = video.title
                imgVideo.setImageResource(video.image)

                root.setOnClickListener {
                    val intent = Intent(context, VideoActivity::class.java)
                    intent.putExtra("id", video.id)
                    context.startActivity(intent)
                }
            }
        }
    }
}