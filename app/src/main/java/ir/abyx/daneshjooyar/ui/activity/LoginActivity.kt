package ir.abyx.daneshjooyar.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.mvp.model.ModelLoginActivity
import ir.abyx.daneshjooyar.mvp.presenter.PresenterLoginActivity
import ir.abyx.daneshjooyar.mvp.view.ViewLoginActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val view = ViewLoginActivity(this, object : ActivityUtils {
            override fun finished() {
                finish()
            }
        })

        setContentView(view.binding.root)
        val presenter = PresenterLoginActivity(this, view, ModelLoginActivity())
        presenter.onCreate()
    }
}