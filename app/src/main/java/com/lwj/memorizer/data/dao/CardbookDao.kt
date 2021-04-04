package com.lwj.memorizer.data.dao

import androidx.room.*
import com.lwj.memorizer.data.entities.Cardbook
import kotlinx.coroutines.flow.Flow

@Dao
interface CardbookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCardbook(cardbook: Cardbook)

    @Update
    suspend fun updateCardBook(cardbook: Cardbook)

    @Query("SELECT * FROM cardbook_list WHERE idx = :key")
    suspend fun getCardbook(key: Long): Cardbook?

    @Query("DELETE FROM cardbook_list WHERE idx = :key")
    suspend fun removeCardbook(key: Long)

    @Query("SELECT * FROM cardbook_list")
    fun getAllCardbooks(): Flow<List<Cardbook>>
}