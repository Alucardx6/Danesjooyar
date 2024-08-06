package ir.abyx.daneshjooyar.mvp.presenter

import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelMainActivity
import ir.abyx.daneshjooyar.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) : BaseLifecycle {

    override fun onCreate() {
        view.setFragment()
        view.initAppBar()
        view.initNav()
    }
}