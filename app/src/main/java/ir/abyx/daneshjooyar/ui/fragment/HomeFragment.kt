package ir.abyx.daneshjooyar.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.mvp.model.ModelHomeFragment
import ir.abyx.daneshjooyar.mvp.presenter.PresenterHomeFragment
import ir.abyx.daneshjooyar.mvp.view.ViewHomeFragment

class HomeFragment(
    private val context: Context,
    private val activityUtils: ActivityUtils
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewHomeFragment(context, activityUtils)
        val presenter = PresenterHomeFragment(context, view, ModelHomeFragment())
        presenter.onCreate()
        return view.binding.root
    }

}