package com.lwj.memorizer.data.dao

import androidx.room.*
import com.lwj.memorizer.data.entities.Cardbook
import kotlinx.coroutines.flow.Flow

@Dao
interface CardbookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCardbook(cardbook: Cardbook)

    @Update
    fun updateCardBook(cardbook: Cardbook)

    @Query("SELECT * FROM cardbook WHERE idx = :key")
    fun getCardbook(key: Long): Cardbook?

    @Query("DELETE FROM cardbook WHERE idx = :key")
    suspend fun removeCardbook(key: Long)

    @Query("SELECT * FROM cardbook")
    fun getAllCardbooks(): Flow<List<Cardbook>>
}