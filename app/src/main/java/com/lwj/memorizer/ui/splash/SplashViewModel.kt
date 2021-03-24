package com.lwj.memorizer.ui.splash

import com.lwj.memorizer.base.BaseViewModel
import com.lwj.memorizer.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: Repository) :BaseViewModel() {
}