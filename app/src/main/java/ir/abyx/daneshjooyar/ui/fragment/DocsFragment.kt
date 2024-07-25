package ir.abyx.daneshjooyar.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.mvp.model.ModelDocsFragment
import ir.abyx.daneshjooyar.mvp.presenter.PresenterDocsFragment
import ir.abyx.daneshjooyar.mvp.view.ViewDocsFragment

class DocsFragment(
    private val context: Context,
    private val activityUtils: ActivityUtils
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewDocsFragment(context, activityUtils)
        val presenter = PresenterDocsFragment(context, view, ModelDocsFragment())
        presenter.onCreate()
        return view.binding.root
    }

}