package ir.abyx.daneshjooyar.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.data.local.dataModel.CatModel
import ir.abyx.daneshjooyar.databinding.RecyclerItemListCatBinding
import ir.abyx.daneshjooyar.mvp.ext.ViewUtils

class RecyclerAdapterCat(
    private val context: Context, private val cats: ArrayList<CatModel>,
    private val viewUtils: ViewUtils,
    private var selectedPosition: Int = 0
) :
    RecyclerView.Adapter<RecyclerAdapterCat.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
        CustomViewHolder(
            RecyclerItemListCatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = cats.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) =
        holder.setData(cats[position], position)

    inner class CustomViewHolder(val binding: RecyclerItemListCatBinding) :
        ViewHolder(binding.root) {
        fun setData(item: CatModel, position: Int) {
            binding.apply {
                if (position == selectedPosition) {
                    txtTitle.setTextColor(ContextCompat.getColor(context, R.color.white_main))
                    itemLayout.setBackgroundResource(R.drawable.back_item_cat_selected)
                } else {
                    txtTitle.setTextColor(ContextCompat.getColor(context, R.color.color_text_black))
                    itemLayout.setBackgroundResource(R.drawable.back_item_cat_default)
                }

                txtTitle.text = item.title
                imgIcon.setImageResource(item.icon)

                root.setOnClickListener {
                    viewUtils.getContent(item.id)
                    selectedPosition = position
                    notifyDataSetChanged()
                }
            }
        }
    }
}
