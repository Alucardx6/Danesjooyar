package ir.abyx.daneshjooyar.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import ir.abyx.daneshjooyar.R
import ir.abyx.daneshjooyar.androidWrapper.ActivityUtils
import ir.abyx.daneshjooyar.databinding.ActivityLoginBinding
import ir.abyx.daneshjooyar.mvp.ext.ToastUtils
import ir.abyx.daneshjooyar.mvp.ext.ViewUtils
import ir.abyx.daneshjooyar.ui.activity.MainActivity
import ir.abyx.daneshjooyar.ui.activity.TermActivity
import java.util.Locale
import java.util.concurrent.TimeUnit


class ViewLoginActivity(private val context: Context, private val activityUtils: ActivityUtils) {

    val binding = ActivityLoginBinding.inflate(LayoutInflater.from(context))
    private var validation: Boolean = false
    private var prevPhone: String = ""
    private var countDownTimer: CountDownTimer? = null

    fun initialize(viewUtils: ViewUtils) {
        binding.apply {

            customButton.disableButton()

            customEditText.setOnTextChangedListener { text ->
                if (text.length == 11) {
                    customButton.enableButton()
                } else {
                    customButton.disableButton()
                }
            }

            pinEntryEditText.addTextChangedListener {
                if (pinEntryEditText.text.toString().length == 5) {
                    customButton.enableButton()
                } else {
                    customButton.disableButton()
                }
            }

            customEditText.clear().setEndIconOnClickListener {
                customEditText.setText("")
            }

            customButton.getView().setOnClickListener {
                if (!validation) {
                    val phone = customEditText.getText()

                    if (!validatePhone(phone)) {
                        customEditText.setError("شماره را صحیح وارد کنید")
                        return@setOnClickListener
                    } else
                        customEditText.setError(null)

                    validatePhase(phone)
                } else {
                    if (validateCode(pinEntryEditText.text.toString())) {
                        viewUtils.saveUser()
                    } else {
                        inputLayoutPin.error = "کد تایید وارد شده نامعتبر است"
                    }
                }
            }

            txtEditPhone.setOnClickListener {
                editPhone()
            }

            txtTerms.setOnClickListener {
                context.startActivity(Intent(context, TermActivity::class.java))
            }

            txtResend.setOnClickListener {
                createTimer()
                ToastUtils.toast(context, "کد مجدد ارسال شد")
            }
        }
    }

    fun result(result: Boolean) {
        if (result) {
            ToastUtils.toast(context, "اطلاعات شما با موفقیت ثبت شد")
            context.startActivity(Intent(context, MainActivity::class.java))
            activityUtils.finished()
        } else
            ToastUtils.toast(context, "مشکلی در ثبت اطلاعات به‌وجود آمده مجدد تلاش کنید")
    }

    @SuppressLint("SetTextI18n")
    private fun validatePhase(phone: String) {
        if (prevPhone != phone || prevPhone.isNullOrEmpty()) {
            stopTimer()
            createTimer()
            prevPhone = phone
        }

        binding.apply {

            if (pinEntryEditText.text.toString().length != 5) {
                customButton.disableButton()
            }

            validation = true
            txtTitle.text = "کد تایید ۵ رقمی را وارد کنید."
            txtDesc.text = "کد تایید برای شماره موبایل $phone ارسال شد."
            customButton.getView().text = "ورود"
            txtEditPhone.visibility = View.VISIBLE
            inputLayoutPin.visibility = View.VISIBLE
            customEditText.visibility = View.INVISIBLE
            linearTerms.visibility = View.GONE
            txtResend.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun editPhone() {
        binding.apply {

            customButton.enableButton()

            validation = false
            txtTitle.text = "ورود یا ثبت نام"
            txtDesc.text = "لطفا شماره موبایل خود را وارد کنید."
            customButton.getView().text = "تایید و ادامه"
            txtEditPhone.visibility = View.GONE
            customEditText.visibility = View.VISIBLE
            linearTerms.visibility = View.VISIBLE
            inputLayoutPin.visibility = View.GONE
            txtResend.visibility = View.GONE
        }
    }

    private fun validateCode(code: String): Boolean {
        return code == "12345"
    }

    private fun createTimer() {
        binding.apply {
            txtResend.setTextColor(ContextCompat.getColor(context, R.color.color_text_gray))
            txtResend.isEnabled = false

            countDownTimer = object : CountDownTimer(15000, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(p0: Long) {
                    val text = java.lang.String.format(
                        Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(p0) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(p0) % 60
                    )
                    txtResend.text = "$text تا ارسال مجدد"
                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    txtResend.text = "ارسال مجدد"
                    txtResend.setTextColor(ContextCompat.getColor(context, R.color.blue))
                    txtResend.isEnabled = true
                    prevPhone = ""
                }

            }.start()
        }
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private fun validatePhone(phone: String): Boolean = phone.matches(Regex("(\\+98|0)?9\\d{9}"))

}