package ir.abyx.daneshjooyar.mvp.model

import android.content.Context
import ir.abyx.daneshjooyar.data.local.preferences.SharedPrefKey

class ModelSplashActivity {

    fun getUser(context: Context): Boolean {
        return try {
            context.getSharedPreferences(SharedPrefKey.PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getBoolean(SharedPrefKey.LOGIN_STATE_KEY, false)
        } catch (e: Exception) {
            false
        }
    }

}