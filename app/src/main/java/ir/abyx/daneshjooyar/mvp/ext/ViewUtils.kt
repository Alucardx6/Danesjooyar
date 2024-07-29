package ir.abyx.daneshjooyar.mvp.ext

interface ViewUtils {

    fun saveUser() {}

    fun getContent(id: Int) {}

    fun startPolling(startPosition: Long) {}

    fun videoStop(stopPosition: Long?, startPosition: Long, currentTime: Long) {}

}