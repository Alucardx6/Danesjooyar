package ir.abyx.daneshjooyar.mvp.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.databinding.ActivityMainBinding
import ir.abyx.daneshjooyar.databinding.CustomDialogTicketBinding
import ir.abyx.daneshjooyar.mvp.ext.ToastUtils
import ir.abyx.daneshjooyar.ui.customView.bottomNav.ActiveFragment
import ir.abyx.daneshjooyar.ui.customView.bottomNav.FragmentType
import ir.abyx.daneshjooyar.ui.fragment.AboutUsFragment
import ir.abyx.daneshjooyar.ui.fragment.DocsFragment
import ir.abyx.daneshjooyar.ui.fragment.HomeFragment

class ViewMainActivity(private val context: Context, private val activityUtils: ActivityUtils) {

    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    fun initialize() {

    }

    fun setFragment() {
        activityUtils.setFragment(HomeFragment())
    }

    fun initNav() {
        binding.customBottomNav.onClickHelper(object : ActiveFragment {
            override fun setFragment(type: FragmentType) {
                val fragment = when (type) {
                    FragmentType.HOME -> HomeFragment()
                    FragmentType.ABOUT_US -> AboutUsFragment()
                    FragmentType.DOCUMENTS -> DocsFragment()
                }

                activityUtils.setFragment(fragment)
            }

        })
    }

    fun initAppBar() {
        binding.customAppBar.support().setOnClickListener {
            val view = CustomDialogTicketBinding.inflate(LayoutInflater.from(context))
            val dialog = Dialog(context)
            dialog.setContentView(view.root)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

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

                ToastUtils.toast(context, "پیام شما ارسال شد")
                dialog.dismiss()
            }
        }
    }
}