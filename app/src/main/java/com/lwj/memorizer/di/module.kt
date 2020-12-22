package com.lwj.memorizer.di

import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.source.DataSource
import com.lwj.memorizer.data.source.remote.RemoteDataSource
import com.lwj.memorizer.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<DataSource> { Repository() }
    viewModel { SplashViewModel(get()) }
}