package com.lwj.memorizer.data.source.remote

import com.lwj.memorizer.data.api.RetrofitService
import com.lwj.memorizer.data.source.DataSource

class RemoteDataSource(
        private val service: RetrofitService
) : DataSource {

    companion object {
        private var INSTANCE: RemoteDataSource? = null

        @JvmStatic
        fun getInstance(service: RetrofitService) =
                INSTANCE ?: synchronized(RemoteDataSource::class.java) {
                    INSTANCE ?: RemoteDataSource(service)
                            .also { INSTANCE = it }
                }
    }
}