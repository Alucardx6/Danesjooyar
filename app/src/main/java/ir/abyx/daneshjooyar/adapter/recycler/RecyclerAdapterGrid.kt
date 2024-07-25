package ir.abyx.daneshjooyar.adapter.recycler

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ir.abyx.daneshjooyar.data.local.dataModel.PanelInfoModel
import ir.abyx.daneshjooyar.databinding.RecyclerItemGridBinding

class RecyclerAdapterGrid(private val data: ArrayList<PanelInfoModel>) :
    RecyclerView.Adapter<RecyclerAdapterGrid.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
        CustomViewHolder(
            RecyclerItemGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.setData(data[position])

    inner class CustomViewHolder(val binding: RecyclerItemGridBinding) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(item: PanelInfoModel) {
            binding.apply {
                imgIcon.setImageResource(item.icon)
                txtTitle.text = item.title
                txtDesc.text = item.desc

                setGradient(txtTitle)
            }
        }
    }

    private fun setGradient(textView: TextView) {
        val gradient = LinearGradient(
            0f, 0f,
            90f, textView.textSize,
            Color.parseColor("#000080"),
            Color.parseColor("#3785CD"),
            Shader.TileMode.CLAMP
        )
        textView.paint.shader = gradient
        textView.invalidate()
    }
}