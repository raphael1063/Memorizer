package com.lwj.memorizer.ui.cardbook

import androidx.activity.viewModels
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.data.entities.ARG_CARDBOOK_KEY
import com.lwj.memorizer.databinding.ActCardbookBinding
import com.lwj.memorizer.ext.showKeyboard
import com.lwj.memorizer.ext.toast
import com.lwj.memorizer.ui.common.CommonAlertDialog
import com.lwj.memorizer.ui.common.CommonAlertDialog.CommonAlertDialogResult
import com.lwj.memorizer.ui.common.CommonAlertDialog.CommonAlertDialogResult.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardbookActivity : BaseActivity<ActCardbookBinding>(
    R.layout.act_cardbook
), CommonAlertDialog.AlertDialogListener {

    private val viewModel by viewModels<CardbookViewModel>()

    override fun start() {
        overridePendingTransition(R.anim.scale_up, R.anim.nothing)
        binding.apply {
            vm = viewModel
        }
        viewModel.setItem(intent.getLongExtra(ARG_CARDBOOK_KEY, -1))
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
                    overridePendingTransition(R.anim.nothing, R.anim.scale_down)
                }
            })
            openExitDialog.observe(this@CardbookActivity, { event ->
                event.getContentIfNotHandled()?.let { title ->
                    CommonAlertDialog.getInstance(
                        R.string.cardbook_back_press_alert_title,
                        getString(R.string.cardbook_back_press_alert_subtitle) + title + "?",
                        canCancel = true
                    ).show(supportFragmentManager, null)
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

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    override fun onAlertDialogResult(result: CommonAlertDialogResult) {
        when (result) {
            OK -> {
                finish()
                overridePendingTransition(R.anim.nothing, R.anim.scale_down)
            }
        }
    }
}