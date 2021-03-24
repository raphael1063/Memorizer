package com.lwj.memorizer.data

import androidx.annotation.WorkerThread
import com.lwj.memorizer.data.dao.CardbookListDao
import com.lwj.memorizer.data.entities.CardbookList
import com.lwj.memorizer.data.source.DataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val cardbookListDao: CardbookListDao) : DataSource {

    fun getAllCardbooks() = cardbookListDao.getAllCardbooks()

    suspend fun removeCardbook(key: Long) = cardbookListDao.removeCardbook(key)

    @WorkerThread
    suspend fun insertCardbook(cardbookList: CardbookList) = cardbookListDao.insertCardbook(cardbookList)

}