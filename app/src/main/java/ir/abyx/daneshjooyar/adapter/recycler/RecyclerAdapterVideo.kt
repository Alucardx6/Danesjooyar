package ir.abyx.daneshjooyar.adapter.recycler

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.data.local.dataModel.CourseVideosModel
import ir.abyx.daneshjooyar.data.local.dataModel.VideoModel
import ir.abyx.daneshjooyar.databinding.RecyclerItemListVideosBinding
import ir.abyx.daneshjooyar.ui.activity.VideoActivity

class RecyclerAdapterVideo(
    private val context: Context,
    private val title: String,
    private val videos: ArrayList<CourseVideosModel>,
    private val videoState: ArrayList<VideoModel>
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
        holder.setData(videos[position], position)

    inner class CustomViewHolder(val binding: RecyclerItemListVideosBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(video: CourseVideosModel, position: Int) {
            binding.apply {
                if (position == 0) {
                    var completed = true

                    videoState.forEach {
                        if (it.percent < 99) {
                            completed = false
                            return@forEach
                        }
                    }

                    if (completed) {
                        txtTitle.visibility = View.GONE
                        txtCongrat.visibility = View.VISIBLE
                        imgVideo.visibility = View.GONE
                        txtCongrat.text = "شما $title را به اتمام رساندید"
                        videoContainer.setBackgroundResource(R.drawable.congrat)
                    } else {
                        val params: ViewGroup.LayoutParams = root.layoutParams
                        params.height = 0
                        root.layoutParams = params
                    }
                } else {
                    txtTitle.visibility = View.VISIBLE
                    txtCongrat.visibility = View.GONE
                    videoContainer.setBackgroundResource(R.drawable.back_item_content)

                    txtTitle.text = video.title
                    imgVideo.setImageResource(video.image)

                    if (videoState[position - 1].percent >= 99)
                        videoContainer.setBackgroundResource(R.drawable.back_item_green)
                }


                root.setOnClickListener {
                    if (position != 0) {
                        val intent = Intent(context, VideoActivity::class.java)
                        intent.putExtra("id", video.id)
                        intent.putExtra("title", video.title)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}