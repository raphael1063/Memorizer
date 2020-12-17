package com.lwj.memorizer.ui.splash

import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.databinding.ActivitySplashBinding
import com.orhanobut.logger.Logger

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(
    R.layout.activity_splash,
    SplashViewModel::class.java
) {
    override fun start() {
    }

    override fun onObserve() {
    }

}