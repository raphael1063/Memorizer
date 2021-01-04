package com.lwj.memorizer.di

import com.lwj.memorizer.ui.cardbook.CardbookViewModel
import com.lwj.memorizer.ui.home.HomeViewModel
import com.lwj.memorizer.ui.login.LoginViewModel
import com.lwj.memorizer.ui.main.MainViewModel
import com.lwj.memorizer.ui.myaccount.MyAccountViewModel
import com.lwj.memorizer.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CardbookViewModel(get()) }
    viewModel { MyAccountViewModel(get()) }
}