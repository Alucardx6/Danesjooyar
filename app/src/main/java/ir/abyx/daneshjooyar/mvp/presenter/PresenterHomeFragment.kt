package ir.abyx.daneshjooyar.mvp.presenter

import android.content.Context
import ir.abyx.daneshjooyar.data.local.dataModel.ContentModel
import ir.abyx.daneshjooyar.data.local.dataModel.MainModel
import ir.abyx.daneshjooyar.data.local.ext.CallbackRequest
import ir.abyx.daneshjooyar.mvp.ext.BaseLifecycle
import ir.abyx.daneshjooyar.mvp.ext.ViewUtils
import ir.abyx.daneshjooyar.mvp.model.ModelHomeFragment
import ir.abyx.daneshjooyar.mvp.view.ViewHomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PresenterHomeFragment(
    private val context: Context, private val view: ViewHomeFragment,
    private val model: ModelHomeFragment
) : BaseLifecycle {

    override fun onCreate() {

        model.getMain(object : CallbackRequest<MainModel> {
            override fun getResponse(response: ArrayList<MainModel>) {
                CoroutineScope(Dispatchers.Main).launch {
                    view.initialize(response[0], object : ViewUtils {
                        override fun getContent(id: Int) {
                            model.getContentById(id, object : CallbackRequest<ContentModel> {
                                override fun getResponse(response: ArrayList<ContentModel>) {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        view.setContent(response)
                                    }
                                }
                            })
                        }
                    })
                }
            }
        })
    }
}