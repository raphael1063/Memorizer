package com.lwj.memorizer.base

import android.app.Application
import com.lwj.memorizer.di.appModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule)
        }
        Logger.addLogAdapter(AndroidLogAdapter(PrettyFormatStrategy.newBuilder().tag("LOGGER").build()))

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
