package com.lwj.memorizer.data

import com.lwj.memorizer.data.source.DataSource

class Repository(
    remoteDataSource: DataSource
) : DataSource {

    companion object {

        private var INSTANCE: Repository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: DataSource) =
            INSTANCE
                ?: synchronized(Repository::class.java) {
                    INSTANCE
                        ?: Repository(remoteDataSource).also { INSTANCE = it }
                }
    }
}