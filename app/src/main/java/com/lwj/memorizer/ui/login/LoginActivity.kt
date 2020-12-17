package com.lwj.memorizer.ui.login

import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    R.layout.activity_login,
    LoginViewModel::class.java
) {
    override fun start() {
    }

    override fun onObserve() {
    }

}