package com.lwj.memorizer.base

import android.app.Application
import com.lwj.memorizer.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule)
        }
        Timber.plant(Timber.DebugTree())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    companion object {
        @Volatile
        private var instance: BaseApplication? = null
    }
}
