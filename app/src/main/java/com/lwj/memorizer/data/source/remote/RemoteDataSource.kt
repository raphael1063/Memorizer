package com.lwj.memorizer.data.source.remote

import com.lwj.memorizer.data.api.ApiService
import com.lwj.memorizer.data.source.DataSource

class RemoteDataSource(
        private val service: ApiService
) : DataSource {

    companion object {
        private var INSTANCE: RemoteDataSource? = null

        @JvmStatic
        fun getInstance(service: ApiService) =
                INSTANCE ?: synchronized(RemoteDataSource::class.java) {
                    INSTANCE ?: RemoteDataSource(service)
                            .also { INSTANCE = it }
                }
    }
}