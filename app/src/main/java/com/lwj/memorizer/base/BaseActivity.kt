package com.lwj.memorizer.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lwj.memorizer.BR
import com.lwj.memorizer.ext.obtainViewModel
import com.lwj.memorizer.ext.toast
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

abstract class BaseActivity<VB : ViewDataBinding, VM: BaseViewModel>(
    @LayoutRes
    private val layoutRes: Int,
    private val viewModelClass: Class<VM>
): AppCompatActivity() {

    protected lateinit var binding: VB

    protected val viewModel: VM
        get() = obtainViewModel(viewModelClass)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel)
        start()
        onObserve()
    }

    private fun onBaseObserve() {
        viewModel.run {
            toastResEvent.observe(this@BaseActivity, { event ->
                event.getContentIfNotHandled()?.let { resId ->
                    this@BaseActivity.toast(resId)
                }
            })
            toastStringEvent.observe(this@BaseActivity, { event ->
                event.getContentIfNotHandled()?.let {  message ->
                    this@BaseActivity.toast(message)
                }
            })
        }
    }

    abstract fun start()
    abstract fun onObserve()
}