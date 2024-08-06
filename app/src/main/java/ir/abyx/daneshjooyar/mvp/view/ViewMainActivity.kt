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
           binding.customAppBar.supportDialog()
        }
    }
}