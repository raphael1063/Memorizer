package com.lwj.memorizer.ui.splash

import android.content.Intent
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.databinding.ActivitySplashBinding
import com.lwj.memorizer.ext.openActivity
import com.lwj.memorizer.ui.login.LoginActivity
import kotlinx.coroutines.*

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(
    R.layout.activity_splash,
    SplashViewModel::class.java
) {

    override fun start() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            openActivity(LoginActivity::class.java)
            finishAffinity()
        }
    }

    override fun onObserve() {
    }
}