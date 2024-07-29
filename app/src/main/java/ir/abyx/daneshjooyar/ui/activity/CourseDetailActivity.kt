package ir.abyx.daneshjooyar.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import ir.abyx.daneshjooyar.adapter.ViewPagerAdapter
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.mvp.model.ModelCourseDetailActivity
import ir.abyx.daneshjooyar.mvp.presenter.PresenterCourseDetailActivity
import ir.abyx.daneshjooyar.mvp.view.ViewCourseDetailActivity

class CourseDetailActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var presenter: PresenterCourseDetailActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val id = intent.getIntExtra("id", 0)

        val view = ViewCourseDetailActivity(this, id, this)
        setContentView(view.binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(view.binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter = PresenterCourseDetailActivity(this, view, ModelCourseDetailActivity())
        presenter.onCreate()

    }

    override fun viewPagerFragment(
        viewPager: ViewPager,
        fragments: List<Fragment>,
        titles: List<String>
    ) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(fragments, titles)
        viewPager.adapter = adapter
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