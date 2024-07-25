package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.data.local.dataModel.CourseInfoModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelCourseInfoFragment
import ir.abyx.daneshjooyar.mvp.view.ViewCourseInfoFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PresenterCourseInfoFragment(
    context: Context, private val view: ViewCourseInfoFragment,
    private val model: ModelCourseInfoFragment
) : BaseLifecycle {
    override fun onCreate() {
        initialize()
    }

    private fun initialize() {
        model.getCourseInfo(object : CallbackRequest<CourseInfoModel> {
            override fun getResponse(response: ArrayList<CourseInfoModel>) {
                CoroutineScope(Dispatchers.Main).launch {
                    view.initialize(response[0])
                }
            }
        })
    }
}