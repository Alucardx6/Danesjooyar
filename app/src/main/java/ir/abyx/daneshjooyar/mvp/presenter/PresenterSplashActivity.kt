package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Intent
import android.os.Handler
import android.os.Looper
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.model.ModelSplashActivity
import ir.abyx.daneshjooyar.mvp.view.ViewSplashActivity
import ir.abyx.daneshjooyar.ui.activity.LoginActivity
import ir.abyx.daneshjooyar.ui.activity.MainActivity

class PresenterSplashActivity(
    private val view: ViewSplashActivity,
    private val model: ModelSplashActivity
) : BaseLifecycle {
    override fun onCreate() {
        navigateToNextScreen(model.getUser(view.context))
    }

    private fun navigateToNextScreen(isUserLoggedIn: Boolean) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (isUserLoggedIn)
                view.context.startActivity(Intent(view.context, MainActivity::class.java))
            else
                view.context.startActivity(Intent(view.context, LoginActivity::class.java))

            view.finish()
        }, 3000)
    }
}