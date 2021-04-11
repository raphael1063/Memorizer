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
import com.lwj.memorizer.ui.common.CommonAlertDialog.CommonAlertDialogResult.CANCEL
import com.lwj.memorizer.ui.common.CommonAlertDialog.CommonAlertDialogResult.OK
import com.orhanobut.logger.Logger

class CommonAlertDialog() : BaseDialogFragment<DialogCommonAlertBinding>(
    R.layout.dialog_common_alert
) {

    private var listener: AlertDialogListener? = null

    private lateinit var result: CommonAlertDialogResult

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is AlertDialogListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        Logger.d("DETACH")
        listener?.onAlertDialogResult(result)
    }

    override fun start() {
        requireDialog().window?.setBackgroundDrawableResource(android.R.color.transparent)
        isCancelable = false
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
            tvAlertCancel.setOnClickListener {
                result = CANCEL
                dismiss()
            }
            tvAlertOk.setOnClickListener {
                result = OK
                dismiss()
            }
        }
    }

    interface AlertDialogListener {
        fun onAlertDialogResult(result: CommonAlertDialogResult)
    }

    enum class CommonAlertDialogResult {
        OK, CANCEL
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