package ir.abyx.daneshjooyar.data.local.ext

interface CallbackRequest<T> {
    fun getResponse(response: ArrayList<T>) {}
    fun getRes(response: T) {}
}