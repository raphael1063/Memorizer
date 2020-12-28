package com.lwj.memorizer.di

import com.lwj.memorizer.data.Repository
import com.lwj.memorizer.data.api.ApiService
import com.lwj.memorizer.data.source.local.LocalDataSource
import com.lwj.memorizer.data.source.remote.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { RemoteDataSource(get() as ApiService) }
    single { LocalDataSource() }
}

val repositoryModule = module {
    single { Repository() }
}