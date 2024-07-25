package ir.abyx.daneshjooyar.mvp.ext

import android.content.Context
import android.widget.Toast

object ToastUtils {
    fun toast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}