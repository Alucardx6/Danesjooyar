package ir.abyx.daneshjooyar.mvp.model

import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.data.remote.RetrofitService
import ir.abyx.daneshjooyar.data.remote.model.MessageModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelMainActivity {
    private val token = "ZRLFruUyzGf7MCSWPxLR855lg5GwZKDmy4XmBdFq"

    fun sendMessage(
        message: String,
        callbackRequest: CallbackRequest<MessageModel>
    ) {

        val service = RetrofitService.mainService

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.sendMessageToTelegram(token, message)

            val result = response.body() as MessageModel

            callbackRequest.getRes(result)
        }
    }

}