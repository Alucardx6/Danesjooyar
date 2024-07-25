package ir.abyx.daneshjooyar.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.databinding.CustomAppBarBinding

class CustomAppBar(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding = CustomAppBarBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)

        initialize(attrs)
    }

    private fun initialize(attrs: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomAppBar)

        val back = typeArray.getBoolean(R.styleable.CustomAppBar_backIcon, false)
        val terms = typeArray.getBoolean(R.styleable.CustomAppBar_terms, false)

        if (back)
            binding.apply {
                imgBack.visibility = View.VISIBLE
                imgMenu.visibility = View.GONE
            }


        if (terms)
            binding.apply {
                imgBack.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
                viewGroupMain.visibility = View.GONE
                imgMenu.visibility = View.GONE
            }

        typeArray.recycle()
    }

    fun showNavDrawer(context: Context) {
        binding.imgMenu.setOnClickListener {

        }
    }

    fun support() = binding.imgSupport

    fun getBackIcon() = binding.imgBack

}