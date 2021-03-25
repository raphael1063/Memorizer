package com.lwj.memorizer.ui.cardbook

import androidx.activity.viewModels
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.databinding.ActCardbookBinding
import com.lwj.memorizer.ext.showKeyboard
import com.lwj.memorizer.ext.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardbookActivity : BaseActivity<ActCardbookBinding>(
        R.layout.act_cardbook
){

    private val viewModel by viewModels<CardbookViewModel>()

    override fun start() {
        binding.apply {
            vm = viewModel
        }
    }

    override fun observe() {
        viewModel.run {
            actionFocusOnTitle.observe(this@CardbookActivity, { event ->
                event.getContentIfNotHandled()?.let {
                    binding.etCardbookTitle.requestFocus()
                    showKeyboard()
                }
            })
            actionFinish.observe(this@CardbookActivity, { event ->
                event.getContentIfNotHandled()?.let {
                    finish()
                }
            })
            toastStringEvent.observe(this@CardbookActivity, { event ->
                event.getContentIfNotHandled()?.let { msg ->
                    toast(msg)
                }
            })
            toastResEvent.observe(this@CardbookActivity, { event ->
                event.getContentIfNotHandled()?.let { resId ->
                    toast(resId)
                }
            })
        }
    }
}