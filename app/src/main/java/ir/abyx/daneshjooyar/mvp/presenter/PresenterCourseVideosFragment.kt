package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.data.local.dataModel.CourseModel
import ir.abyx.daneshjooyar.data.local.dataModel.CourseVideosModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelCourseVideosFragment
import ir.abyx.daneshjooyar.mvp.view.ViewCourseVideosFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PresenterCourseVideosFragment(
    context: Context,
    private val view: ViewCourseVideosFragment,
    private val model: ModelCourseVideosFragment
) : BaseLifecycle {
    override fun onCreate() {
        initialize()
    }

    fun initialize() {
        model.getVideoList(object : CallbackRequest<CourseModel> {
            override fun getRes(response: CourseModel) {
                CoroutineScope(Dispatchers.Main).launch {
                    view.initialize(response)
                }
            }
        })
    }
}