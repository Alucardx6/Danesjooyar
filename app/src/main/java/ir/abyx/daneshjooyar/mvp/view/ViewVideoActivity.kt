package ir.abyx.daneshjooyar.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.databinding.ActivityVideoBinding

class ViewVideoActivity(private val context: Context, private val activityUtils: ActivityUtils) {

    private lateinit var player: SimpleExoPlayer

    val binding = ActivityVideoBinding.inflate(LayoutInflater.from(context))

    fun initialize() {
        val videoFilePath = "android.resource://${context.packageName}/${R.raw.hitler}"
        var isFullscreen = false

        binding.apply {
            //region videoPlayer
            val playerView = videoPlayer
            val btnFullscreen = playerView.findViewById<ImageView>(R.id.img_fullscreen)

            player = SimpleExoPlayer.Builder(context)
                .setSeekBackIncrementMs(5000)
                .setSeekForwardIncrementMs(5000)
                .build()
            playerView.player = player
            playerView.keepScreenOn = true

            player.addListener(object : Player.Listener {})

            val mediaItem = MediaItem.fromUri(videoFilePath)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()

            btnFullscreen.setOnClickListener {
                isFullscreen = !isFullscreen
                if (isFullscreen) {
                    btnFullscreen.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_exit_fullscreen
                        )
                    )
                } else {
                    btnFullscreen.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_fullscreen
                        )
                    )
                }
                activityUtils.fullScreen(isFullscreen)
            }
            //endregion

            //region progressBar
            val lottieAnimationView = lottieAnimationView
            progressBar.isEnabled = false
            progressBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val thumbX = seekBar?.thumb?.bounds?.left ?: 0
                    val newX = (thumbX - lottieAnimationView.width / 2 + 16).toFloat()
                    lottieAnimationView.x = maxOf(0f, newX)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
            //endregion
        }
    }

    fun backButton() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            activityUtils.finished()
        }
    }

    fun stopPlayer() {
        player.stop()
    }

    fun releasePlayer() {
        player.release()
    }

}