package ir.abyx.daneshjooyar.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.databinding.CustomButtonBinding

class CustomButton(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding = CustomButtonBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)

        initialize(attrs)
    }

    private fun initialize(attrs: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton)
        val text = typeArray.getString(R.styleable.CustomButton_text)
        binding.customButton.text = text
        typeArray.recycle()
    }

    fun getView() = binding.customButton

    fun disableButton() {
        binding.apply {
            customButton.isEnabled = false
            customButton.alpha = 0.5f
        }
    }

    fun enableButton() {
        binding.apply {
            customButton.isEnabled = true
            customButton.alpha = 1.0f
        }
    }
}