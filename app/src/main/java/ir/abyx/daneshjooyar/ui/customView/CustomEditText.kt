package ir.abyx.daneshjooyar.ui.customView

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.databinding.CustomEditTextBinding

class CustomEditText(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding = CustomEditTextBinding.inflate(LayoutInflater.from(context))
    private var onTextChangedListener: ((String) -> Unit)? = null

    init {
        addView(binding.root)

        initialize(attrs)
    }

    private fun initialize(attrs: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)

        val hint = typeArray.getString(R.styleable.CustomEditText_hintText)
        val type = typeArray.getInteger(R.styleable.CustomEditText_type, 1)
        val rtlSupport =
            typeArray.getBoolean(R.styleable.CustomEditText_rtlSupport, false)
        val maxLength = typeArray.getInteger(R.styleable.CustomEditText_max, 0)
        val centerGravity =
            typeArray.getBoolean(R.styleable.CustomEditText_centerGravity, false)

        binding.apply {
            textInputLayout.hint = hint
            textInputLayout.isStartIconVisible = false
            textInputEditText.inputType = type

            if (rtlSupport) {
                textInputEditText.textDirection = TEXT_DIRECTION_RTL
                textInputLayout.textDirection = LAYOUT_DIRECTION_RTL
            }

            if (maxLength > 0)
                textInputEditText.filters = arrayOf(InputFilter.LengthFilter(maxLength))

            if (centerGravity)
                textInputEditText.gravity = Gravity.CENTER

            typeArray.recycle()

            textInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    onTextChangedListener?.invoke(s.toString())
                    textInputLayout.isStartIconVisible = !s.isNullOrEmpty()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

    }

    fun setOnTextChangedListener(listener: (String) -> Unit) {
        onTextChangedListener = listener
    }

    fun setError(errorText: String?) {
        binding.textInputLayout.error = errorText
    }

    fun getText() = binding.textInputEditText.text.toString()

    fun setText(text: String) {
        binding.textInputEditText.text = Editable.Factory().newEditable(text)
    }

    fun getEditText() = binding.textInputEditText

    fun clear() = binding.textInputLayout

}