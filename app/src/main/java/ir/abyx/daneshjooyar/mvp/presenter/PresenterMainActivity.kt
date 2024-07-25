package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelMainActivity
import ir.abyx.daneshjooyar.mvp.view.ViewMainActivity

class PresenterMainActivity(
    context: Context, private val view: ViewMainActivity,
    private val model: ModelMainActivity
) : BaseLifecycle {

    override fun onCreate() {
        view.setFragment()
        view.initAppBar()
        view.initNav()
    }

    private fun initialize() {
        view.initialize()
    }
}