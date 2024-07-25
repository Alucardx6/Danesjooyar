package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.ext.ToastUtils
import ir.abyx.daneshjooyar.mvp.ext.ViewUtils
import ir.abyx.daneshjooyar.mvp.model.ModelLoginActivity
import ir.abyx.daneshjooyar.mvp.view.ViewLoginActivity

class PresenterLoginActivity(
    private val context: Context,
    private val view: ViewLoginActivity,
    private val model: ModelLoginActivity
) : BaseLifecycle {
    override fun onCreate() {
        initialize()
    }

    private fun initialize() {
        view.initialize(object : ViewUtils {
            override fun saveUser() {
                view.result(model.saveUser(context))
            }
        })
    }

}