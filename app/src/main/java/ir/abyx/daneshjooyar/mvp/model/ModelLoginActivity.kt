package ir.abyx.daneshjooyar.mvp.model

import android.content.Context
import ir.abyx.daneshjooyar.data.local.preferences.SharedPrefKey

class ModelLoginActivity {

    fun saveUser(context: Context) =
        context.getSharedPreferences(SharedPrefKey.PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
            .putBoolean(SharedPrefKey.LOGIN_STATE_KEY, true).commit()
}