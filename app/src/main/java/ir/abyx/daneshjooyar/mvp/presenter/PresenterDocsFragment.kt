package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelDocsFragment
import ir.abyx.daneshjooyar.mvp.view.ViewDocsFragment

class PresenterDocsFragment(
    context: Context,
    private val view: ViewDocsFragment,
    private val model: ModelDocsFragment
): BaseLifecycle {
    override fun onCreate() {

    }
}