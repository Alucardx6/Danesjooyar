package ir.abyx.daneshjooyar.ui.customView.bottomNav

enum class FragmentType {
    HOME, ABOUT_US, DOCUMENTS
}

interface ActiveFragment {
    fun setFragment(type: FragmentType)
}