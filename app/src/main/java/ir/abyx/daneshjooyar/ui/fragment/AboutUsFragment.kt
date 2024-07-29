package ir.abyx.daneshjooyar.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.abyx.daneshjooyar.mvp.model.ModelAboutUsFragment
import ir.abyx.daneshjooyar.mvp.presenter.PresenterAboutUsFragment
import ir.abyx.daneshjooyar.mvp.view.ViewAboutUsFragment

class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewAboutUsFragment(requireContext())
        val presenter = PresenterAboutUsFragment(requireContext(), view, ModelAboutUsFragment())
        presenter.onCreate()
        return view.binding.root
    }

}