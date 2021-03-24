package com.lwj.memorizer.data.dao

import androidx.room.*
import com.lwj.memorizer.data.entities.CardbookList
import kotlinx.coroutines.flow.Flow

@Dao
interface CardbookListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCardbook(cardbookList: CardbookList)

    @Update
    fun updateCardBook(cardbookList: CardbookList)

    @Query("SELECT * FROM cardbook_list WHERE idx = :key")
    fun getCardbook(key: Long): CardbookList?

    @Query("DELETE FROM cardbook_list WHERE idx = :key")
    suspend fun removeCardbook(key: Long)

    @Query("SELECT * FROM cardbook_list")
    fun getAllCardbooks(): Flow<List<CardbookList>>
}