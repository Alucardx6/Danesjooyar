package ir.abyx.daneshjooyar.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.mvp.model.ModelAboutUsFragment
import ir.abyx.daneshjooyar.mvp.model.ModelDocsFragment
import ir.abyx.daneshjooyar.mvp.presenter.PresenterAboutUsFragment
import ir.abyx.daneshjooyar.mvp.presenter.PresenterDocsFragment
import ir.abyx.daneshjooyar.mvp.view.ViewAboutUsFragment
import ir.abyx.daneshjooyar.mvp.view.ViewDocsFragment

class AboutUsFragment(
    private val context: Context,
    private val activityUtils: ActivityUtils
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewAboutUsFragment(context, activityUtils)
        val presenter = PresenterAboutUsFragment(context, view, ModelAboutUsFragment())
        presenter.onCreate()
        return view.binding.root
    }

}