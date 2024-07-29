package ir.abyx.daneshjooyar.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ir.abyx.daneshjooyar.R
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
        val title = intent.getStringExtra("title")

        setContentView(view.binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(view.binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter = PresenterVideoActivity(
            this, view, ModelVideoActivity(
                id, title!!, this
            )
        )
        presenter.onCreate()
    }

    override fun fullScreen(isFullscreen: Boolean) {
        requestedOrientation = if (isFullscreen) {

            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

            view.binding.viewGroupFullscreen.visibility = View.GONE

            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            view.binding.videoCardView.layoutParams = layoutParams


            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {

            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

            view.binding.viewGroupFullscreen.visibility = View.VISIBLE

            val constraintLayout = findViewById<ConstraintLayout>(ir.abyx.daneshjooyar.R.id.main)
            val constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)
            constraintSet.connect(
                R.id.video_card_view,
                ConstraintSet.TOP,
                R.id.txt_video_title,
                ConstraintSet.BOTTOM,
                36
            )
            constraintSet.connect(
                R.id.video_card_view,
                ConstraintSet.RIGHT,
                R.id.main,
                ConstraintSet.RIGHT,
                36
            )
            constraintSet.connect(
                R.id.video_card_view,
                ConstraintSet.LEFT,
                R.id.main,
                ConstraintSet.LEFT,
                36
            )
            constraintSet.applyTo(constraintLayout)

            val layoutParams =
                view.binding.videoCardView.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.height = resources.getDimensionPixelSize(R.dimen.your_cad_view_height)
            view.binding.videoCardView.layoutParams = layoutParams

            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
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