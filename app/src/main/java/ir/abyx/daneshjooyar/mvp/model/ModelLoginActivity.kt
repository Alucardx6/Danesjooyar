package ir.abyx.daneshjooyar.mvp.model

import android.content.Context
import ir.abyx.pastry.data.local.preferences.SecureSharePref
import ir.abyx.pastry.data.local.preferences.SharedPrefKey

class ModelLoginActivity {

    fun saveUser(context: Context) =
        SecureSharePref.getSharedPref(context).edit()
            .putBoolean(SharedPrefKey.LOGIN_STATE_KEY, true).commit()
}