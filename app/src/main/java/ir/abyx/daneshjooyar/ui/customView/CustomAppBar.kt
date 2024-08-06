package ir.abyx.daneshjooyar.ui.customView

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.data.remote.RetrofitService
import ir.abyx.daneshjooyar.databinding.CustomAppBarBinding
import ir.abyx.daneshjooyar.databinding.CustomDialogTicketBinding
import ir.abyx.daneshjooyar.mvp.ext.ToastUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomAppBar(
    private val context: Context,
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

    fun showNavDrawer() {
        binding.imgMenu.setOnClickListener {

        }
    }

    fun support() = binding.imgSupport

    fun getBackIcon() = binding.imgBack

    fun supportDialog() {
        val view = CustomDialogTicketBinding.inflate(LayoutInflater.from(context))
        val dialog = Dialog(context)
        dialog.setContentView(view.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        view.customButton.enableButton()

        view.imgCancel.setOnClickListener {
            dialog.dismiss()
        }

        view.customButton.getView().setOnClickListener {
            val title = view.customEdtTitle
            val desc = view.customEdtDesc

            title.setError(null)
            desc.setError(null)

            if (title.getText().isNullOrEmpty()) {
                title.setError("لطفا عنوان را وارد کنید")
                return@setOnClickListener
            }
            if (title.getText().trim().length < 5) {
                title.setError("عنوان نمیتواند کمتر از ۵ کارکتر باشد")
                return@setOnClickListener
            }

            if (desc.getText().isNullOrEmpty()) {
                desc.setError("لطفا توضیحات خود را وارد کنید")
                return@setOnClickListener
            }
            if (desc.getText().trim().length < 10) {
                desc.setError("توضیحات نمیتواند کمتر از ۱۰ کارکتر باشد")
                return@setOnClickListener
            }

            view.customButton.disableButton()

            CoroutineScope(Dispatchers.IO).launch {
                val response = RetrofitService.mainService.sendMessageToTelegram(
                    "ZRLFruUyzGf7MCSWPxLR855lg5GwZKDmy4XmBdFq",
                    "عنوان: ${title.getText()} \nتوضیحات: ${desc.getText()}"
                )

                withContext(Dispatchers.Main) {
                    if(response.isSuccessful) {
                        ToastUtils.toast(context, "پیام شما با موفقیت ارسال شد.")
                        dialog.dismiss()
                    } else {
                        ToastUtils.toast(context, "مشکلی در ارسال پیام پیش آمده، مجددا تلاش کنید.")
                        view.customButton.enableButton()
                    }
                }
            }
        }
    }

}