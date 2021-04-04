package com.lwj.memorizer.data

import androidx.annotation.WorkerThread
import com.lwj.memorizer.data.dao.CardbookDao
import com.lwj.memorizer.data.entities.Cardbook
import com.lwj.memorizer.data.source.DataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val cardbookDao: CardbookDao) : DataSource {

    fun getAllCardbookLists() = cardbookDao.getAllCardbooks()

    suspend fun getCardbook(key: Long) = cardbookDao.getCardbook(key)

    suspend fun updateCardbook(cardbook: Cardbook) = cardbookDao.updateCardBook(cardbook)

    suspend fun removeCardbook(key: Long) = cardbookDao.removeCardbook(key)

    @WorkerThread
    suspend fun insertCardbook(cardbook: Cardbook) = cardbookDao.insertCardbook(cardbook)

}