package ir.abyx.daneshjooyar.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.mvp.ext.ToastUtils
import ir.abyx.daneshjooyar.mvp.model.ModelMainActivity
import ir.abyx.daneshjooyar.mvp.presenter.PresenterMainActivity
import ir.abyx.daneshjooyar.mvp.view.ViewMainActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var view: ViewMainActivity
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        view = ViewMainActivity(this, this)

        setContentView(view.binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(view.binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val presenter = PresenterMainActivity(view, ModelMainActivity())
        presenter.onCreate()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    exitProcess(0)
                } else {
                    doubleBackToExitPressedOnce = true
                    ToastUtils.toast(this@MainActivity, "برای خروج مجدد بازگشت بزنید")

                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleBackToExitPressedOnce = false
                    }, 2000)
                }
            }
        })
    }

    override fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, fragment)
            .commit()
    }

    override fun finish() {
        finished()
    }
}