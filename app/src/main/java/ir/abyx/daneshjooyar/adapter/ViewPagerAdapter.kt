package ir.abyx.daneshjooyar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(supportFragmentManager) {

    // declare arrayList to contain fragments and its title
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        // return a particular fragment page
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        // return the number of tabs
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        // return title of the tab
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment: List<Fragment>, title: List<String>) {
        fragment.forEach {
            mFragmentList.add(it)
        }
        title.forEach {
            mFragmentTitleList.add(it)
        }
    }
}