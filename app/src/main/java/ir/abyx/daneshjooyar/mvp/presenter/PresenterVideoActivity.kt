package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelVideoActivity
import ir.abyx.daneshjooyar.mvp.view.ViewVideoActivity

class PresenterVideoActivity(
    context: Context, private val view: ViewVideoActivity,
    private val model: ModelVideoActivity
) : BaseLifecycle {

    override fun onCreate() {
        view.backButton()
        initialize()
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