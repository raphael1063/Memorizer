package com.lwj.memorizer.ui.common

import android.content.Context
import android.os.Bundle
import androidx.core.view.isVisible
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseDialogFragment
import com.lwj.memorizer.data.entities.ARG_CAN_CANCEL
import com.lwj.memorizer.data.entities.ARG_SUBTITLE
import com.lwj.memorizer.data.entities.ARG_TITLE
import com.lwj.memorizer.databinding.DialogCommonAlertBinding
import com.lwj.memorizer.ui.common.CommonAlertDialog.CommonAlertDialogResult.OK

class CommonAlertDialog() : BaseDialogFragment<DialogCommonAlertBinding>(
    R.layout.dialog_common_alert
) {

    private var listener: AlertDialogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is AlertDialogListener) {
            listener = context
        }
    }

    override fun start() {
        requireDialog().window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun setBinding() {
        val titleResId = requireArguments().getInt(ARG_TITLE)
        val subtitle = requireArguments().getString(ARG_SUBTITLE)
        val canCancel = requireArguments().getBoolean(ARG_CAN_CANCEL)
        binding.apply {
            tvAlertTitle.text = resources.getString(titleResId)
            tvAlertSubtitle.text = subtitle
            if(canCancel) {
                tvAlertCancel.isVisible = true
                viewAlertButtonDivider.isVisible = true
            }
            tvAlertCancel.setOnClickListener { dismiss() }
            tvAlertOk.setOnClickListener { listener?.onAlertDialogResult(OK) }
        }
    }

    interface AlertDialogListener {
        fun onAlertDialogResult(result: CommonAlertDialogResult)
    }

    enum class CommonAlertDialogResult {
        OK
    }

    companion object {
        fun getInstance(titleResId: Int, subtitle: String, canCancel: Boolean) = CommonAlertDialog().apply {
            arguments = Bundle().apply {
                putInt(ARG_TITLE, titleResId)
                putString(ARG_SUBTITLE, subtitle)
                putBoolean(ARG_CAN_CANCEL, canCancel)
            }
        }
    }
}