package ir.abyx.daneshjooyar.mvp.ext

interface BaseLifecycle {

    fun onCreate()

    fun onResume() {}

    fun onStop() {}

    fun onDestroy() {}

    fun onPause() {}

}