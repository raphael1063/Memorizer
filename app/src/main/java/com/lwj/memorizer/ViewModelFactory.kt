package com.lwj.memorizer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.api.RetrofitClient
import com.lwj.memorizer.data.source.remote.RemoteDataSource
import com.lwj.memorizer.ui.cardbook.CardBookViewModel
import com.lwj.memorizer.ui.home.HomeViewModel
import com.lwj.memorizer.ui.login.LoginViewModel
import com.lwj.memorizer.ui.main.MainViewModel
import com.lwj.memorizer.ui.myaccount.MyAccountViewModel
import com.lwj.memorizer.ui.splash.SplashViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
        private val repository: Repository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(SplashViewModel::class.java) ->
                    SplashViewModel(repository)
                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(repository)
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(repository)
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(repository)
                isAssignableFrom(CardBookViewModel::class.java) ->
                    CardBookViewModel(repository)
                isAssignableFrom(MyAccountViewModel::class.java) ->
                    MyAccountViewModel(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    Repository.getInstance(
                        RemoteDataSource.getInstance(RetrofitClient.client)
                    )
                ).also { INSTANCE = it }
            }
    }
}
