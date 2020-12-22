package com.lwj.memorizer.ui.splash

import android.content.Intent
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.databinding.ActivitySplashBinding
import com.lwj.memorizer.ui.login.LoginActivity
import kotlinx.coroutines.*

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(
    R.layout.activity_splash,
    SplashViewModel::class.java
) {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun start() {
        activityScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finishAffinity()
        }
    }

    override fun onPause() {
        super.onPause()
        activityScope.cancel()
    }

    override fun onObserve() {
    }
}