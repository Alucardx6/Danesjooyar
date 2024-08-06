package ir.abyx.daneshjooyar.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.abyx.daneshjooyar.mvp.ext.ToastUtils
import ir.abyx.daneshjooyar.mvp.model.ModelHomeFragment
import ir.abyx.daneshjooyar.mvp.presenter.PresenterHomeFragment
import ir.abyx.daneshjooyar.mvp.view.ViewHomeFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewHomeFragment(requireContext())
        val presenter = PresenterHomeFragment(requireContext(), view, ModelHomeFragment())
        presenter.onCreate()
        return view.binding.root
    }
}