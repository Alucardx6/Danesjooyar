package ir.abyx.daneshjooyar.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.mvp.model.ModelSplashActivity
import ir.abyx.daneshjooyar.mvp.presenter.PresenterSplashActivity
import ir.abyx.daneshjooyar.mvp.view.ViewSplashActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val view = ViewSplashActivity(this, object : ActivityUtils {
            override fun finished() {
                finish()
            }
        })

        setContentView(view.binding.main)
        val presenter = PresenterSplashActivity(view, ModelSplashActivity())
        presenter.onCreate()
    }
}