package ir.abyx.daneshjooyar.mvp.model

import android.content.Context
import ir.abyx.pastry.data.local.preferences.SecureSharePref
import ir.abyx.pastry.data.local.preferences.SharedPrefKey

class ModelSplashActivity {

    fun getUser(context: Context): Boolean {
        return try {
            SecureSharePref.getSharedPref(context).getBoolean(SharedPrefKey.LOGIN_STATE_KEY, false)
        } catch (e: Exception) {
            false
        }
    }

}