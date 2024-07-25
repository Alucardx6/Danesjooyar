package ir.abyx.daneshjooyar.mvp.view

import android.content.Context
import android.view.LayoutInflater
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.databinding.ActivitySplashBinding

class ViewSplashActivity(val context: Context, val activityUtils: ActivityUtils) {

    val binding = ActivitySplashBinding.inflate(LayoutInflater.from(context))

    fun finish() {
        activityUtils.finished()
    }
}