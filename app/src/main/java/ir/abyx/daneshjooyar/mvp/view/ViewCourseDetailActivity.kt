package ir.abyx.daneshjooyar.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.databinding.ActivityCourseDetailBinding
import ir.abyx.daneshjooyar.ui.fragment.CourseInfoFragment
import ir.abyx.daneshjooyar.ui.fragment.CourseVideosFragment


class ViewCourseDetailActivity(
    private val context: Context,
    private val activityUtils: ActivityUtils
) {

    private lateinit var player: SimpleExoPlayer

    val binding = ActivityCourseDetailBinding.inflate(LayoutInflater.from(context))

    fun initialize() {
        val videoFilePath = "android.resource://${context.packageName}/${R.raw.hitler}"

        binding.apply {

            videoPlayer.clipToOutline
            videoPlayer.findViewById<ImageView>(R.id.img_fullscreen).visibility = View.GONE

            player = SimpleExoPlayer.Builder(context)
                .setSeekBackIncrementMs(5000)
                .setSeekForwardIncrementMs(5000)
                .build()
            videoPlayer.player = player
            videoPlayer.keepScreenOn = true

            player.addListener(object : Player.Listener {})

            val mediaItem = MediaItem.fromUri(videoFilePath)
            player.setMediaItem(mediaItem)
            player.prepare()
        }

    }

    fun initTab(title: String) {
        binding.apply {
            activityUtils.viewPagerFragment(
                viewPager, listOf(
                    CourseVideosFragment(activityUtils, title),
                    CourseInfoFragment(activityUtils)
                ), listOf("ویدیو‌ ها", "اطلاعات")
            )

            tabs.setupWithViewPager(viewPager)
        }
    }

    fun initAppBar() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            activityUtils.finished()
        }

        binding.customAppBar.support().setOnClickListener {
            binding.customAppBar.supportDialog()
        }
    }

    fun stopPlayer() {
        player.stop()
    }

    fun releasePlayer() {
        player.release()
    }

}