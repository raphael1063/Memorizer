package com.lwj.memorizer.ui.login

import android.content.Intent
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.databinding.ActivityLoginBinding
import com.lwj.memorizer.ext.openActivity
import com.lwj.memorizer.ui.home.HomeActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    R.layout.activity_login,
    LoginViewModel::class.java
) {
    override fun start() {
    }

    override fun onObserve() {
        viewModel.run {
            actionLogin.observe(this@LoginActivity, { event ->
                event.getContentIfNotHandled()?.let {
                    openActivity(HomeActivity::class.java)
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finishAffinity()
                }
            })
        }
    }
}