package ir.abyx.daneshjooyar.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
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
    private var fullScreen = false

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

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (fullScreen) {
                    showUI()
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    fullScreen = false
                } else {
                    finish()
                }
            }
        })

    }

    override fun fullScreen(isFullscreen: Boolean) {
        fullScreen = isFullscreen
        requestedOrientation = if (isFullscreen) {
            hideUI()
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            showUI()
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    private fun hideUI() {

        view.binding.videoPlayer.findViewById<ImageView>(R.id.img_fullscreen)
            .setImageDrawable(
                ContextCompat.getDrawable(
                    this@VideoActivity,
                    R.drawable.ic_exit_fullscreen
                )
            )

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        view.binding.viewGroupFullscreen.visibility = View.GONE

        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        view.binding.videoCardView.layoutParams = layoutParams
    }

    private fun showUI() {

        view.binding.videoPlayer.findViewById<ImageView>(R.id.img_fullscreen)
            .setImageDrawable(
                ContextCompat.getDrawable(
                    this@VideoActivity,
                    R.drawable.ic_fullscreen
                )
            )

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

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