package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelAboutUsFragment
import ir.abyx.daneshjooyar.mvp.view.ViewAboutUsFragment

class PresenterAboutUsFragment(
    private val context: Context,
    private val view: ViewAboutUsFragment,
    private val model: ModelAboutUsFragment
) : BaseLifecycle {
    override fun onCreate() {

    }
}