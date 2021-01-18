package com.lwj.memorizer.di

import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.ui.cardbook.CardbookAdapter
import com.lwj.memorizer.ui.cardbook.CardbookViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun repository() = Repository()
}