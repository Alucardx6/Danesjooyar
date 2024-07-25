package ir.abyx.daneshjooyar.androidWrapper

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

interface ActivityUtils {

    fun finished() {}

    fun setFragment(fragment: Fragment) {}

    fun viewPagerFragment(
        viewPager: ViewPager,
        fragments: List<Fragment>,
        titles: List<String>
    ) {
    }

    fun fullScreen(isFullscreen: Boolean) {}

}