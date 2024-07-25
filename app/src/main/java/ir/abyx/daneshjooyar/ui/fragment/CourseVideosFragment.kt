package ir.abyx.daneshjooyar.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.mvp.model.ModelCourseVideosFragment
import ir.abyx.daneshjooyar.mvp.presenter.PresenterCourseVideosFragment
import ir.abyx.daneshjooyar.mvp.view.ViewCourseVideosFragment

class CourseVideosFragment(
    private val context: Context,
    private val activityUtils: ActivityUtils
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewCourseVideosFragment(context, activityUtils)
        val presenter = PresenterCourseVideosFragment(context, view, ModelCourseVideosFragment())
        presenter.onCreate()
        return view.binding.root
    }

}