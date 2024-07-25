package ir.abyx.daneshjooyar.ui.customView.bottomNav

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.databinding.CustomButtomNavBinding

class CustomBottomNav(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding = CustomButtomNavBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun onClickHelper(activeFragment: ActiveFragment) {

        binding.apply {
            txtHome.setOnClickListener {
                selected(binding.txtHome)
                activeFragment.setFragment(FragmentType.HOME)
            }

            txtAboutUs.setOnClickListener {
                selected(binding.txtAboutUs)
                activeFragment.setFragment(FragmentType.ABOUT_US)
            }

            txtDocuments.setOnClickListener {
                selected(binding.txtDocuments)
                activeFragment.setFragment(FragmentType.DOCUMENTS)
            }
        }
    }

    private fun selected(view: TextView) {
        binding.apply {
            default(txtHome)
            default(txtAboutUs)
            default(txtDocuments)
        }

        view.setTextColor(ContextCompat.getColor(context, R.color.blue))
        when (view.id) {
            R.id.txt_home -> {
                view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_selected, 0, 0)
            }

            R.id.txt_about_us -> {
                view.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    R.drawable.ic_about_us_selected,
                    0,
                    0
                )
            }

            R.id.txt_documents -> {
                view.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    R.drawable.ic_documents_selected,
                    0,
                    0
                )
            }
        }
//        view.compoundDrawables.forEach { drawable ->
//            drawable?.setTint(ContextCompat.getColor(context, R.color.blue))
//        }
    }

    private fun default(view: TextView) {
        view.setTextColor(ContextCompat.getColor(context, R.color.color_text_gray))
        when (view.id) {
            R.id.txt_home -> {
                view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home, 0, 0)
            }

            R.id.txt_about_us -> {
                view.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    R.drawable.ic_about_us,
                    0,
                    0
                )
            }

            R.id.txt_documents -> {
                view.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    R.drawable.ic_documents,
                    0,
                    0
                )
            }
        }
//        view.compoundDrawables.forEach { drawable ->
//            drawable?.setTint(ContextCompat.getColor(context, R.color.color_icon_default))
//        }
    }

}