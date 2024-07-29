package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.data.local.dataModel.AboutModel
import ir.abyx.daneshjooyar.data.local.dataModel.CourseInfoModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelAboutUsFragment
import ir.abyx.daneshjooyar.mvp.view.ViewAboutUsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PresenterAboutUsFragment(
    private val context: Context,
    private val view: ViewAboutUsFragment,
    private val model: ModelAboutUsFragment
) : BaseLifecycle {
    override fun onCreate() {
        initialize()
    }

    private fun initialize() {
        model.getCourseInfo(object : CallbackRequest<AboutModel> {
            override fun getResponse(response: ArrayList<AboutModel>) {
                CoroutineScope(Dispatchers.Main).launch {
                    view.initialize(response[0])
                }
            }
        })
    }
}