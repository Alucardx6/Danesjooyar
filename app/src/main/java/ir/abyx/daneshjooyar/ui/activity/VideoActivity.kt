package ir.abyx.daneshjooyar.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.mvp.model.ModelVideoActivity
import ir.abyx.daneshjooyar.mvp.presenter.PresenterVideoActivity
import ir.abyx.daneshjooyar.mvp.view.ViewVideoActivity

class VideoActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var presenter: PresenterVideoActivity
    private lateinit var view: ViewVideoActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        view = ViewVideoActivity(this, this)

        val id = intent.getIntExtra("id", 0)

        setContentView(view.binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(view.binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter = PresenterVideoActivity(this, view, ModelVideoActivity(id, this
        ))
        presenter.onCreate()
    }

    override fun fullScreen(isFullscreen: Boolean) {
        requestedOrientation = if (isFullscreen)
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        else
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun finished() {
        finish()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onPause() {
        super.onPause()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}