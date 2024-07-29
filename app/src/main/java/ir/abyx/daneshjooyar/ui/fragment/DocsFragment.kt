package ir.abyx.daneshjooyar.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.abyx.daneshjooyar.mvp.model.ModelDocsFragment
import ir.abyx.daneshjooyar.mvp.presenter.PresenterDocsFragment
import ir.abyx.daneshjooyar.mvp.view.ViewDocsFragment

class DocsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewDocsFragment(requireContext())
        val presenter = PresenterDocsFragment(requireContext(), view, ModelDocsFragment())
        presenter.onCreate()
        return view.binding.root
    }

}