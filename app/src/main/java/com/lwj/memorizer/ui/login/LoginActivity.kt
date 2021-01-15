package com.lwj.memorizer.ui.login

import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.databinding.ActivityLoginBinding
import com.lwj.memorizer.ext.openActivity
import com.lwj.memorizer.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {

    private val viewModel: LoginViewModel by lazy {
        LoginViewModel()
    }

    override fun start() {
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@LoginActivity
        }
    }

    override fun onObserve() {
        viewModel.run {
            actionLogin.observe(this@LoginActivity, { event ->
                event.getContentIfNotHandled()?.let {
                    openActivity(MainActivity::class.java)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finishAffinity()
                }
            })
        }
    }
}