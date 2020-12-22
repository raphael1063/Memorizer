package com.lwj.memorizer.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lwj.memorizer.R
import com.lwj.memorizer.base.BaseActivity
import com.lwj.memorizer.databinding.ActivityHomeBinding
import com.lwj.memorizer.ext.toast

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(
    R.layout.activity_home,
   HomeViewModel::class.java
) {
    override fun start() {
        toast("HomeActivityStarted!")
    }

    override fun onObserve() {
    }
}