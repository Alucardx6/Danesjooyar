package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelCourseDetailActivity
import ir.abyx.daneshjooyar.mvp.view.ViewCourseDetailActivity

class PresenterCourseDetailActivity(
    context: Context,
    private val view: ViewCourseDetailActivity,
    private val model: ModelCourseDetailActivity
) : BaseLifecycle {
    override fun onCreate() {
        initialize()
        view.initTab()
        view.backButton()
    }

    private fun initialize() {
        view.initialize()
    }

    override fun onStop() {
        view.stopPlayer()
    }

    override fun onDestroy() {
        view.releasePlayer()
    }
}