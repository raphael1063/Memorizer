package com.lwj.memorizer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lwj.memorizer.data.entities.Cardbook

@Dao
interface CardbookDao {

    @Insert
    fun insertCardbook(cardbook: Cardbook)

    @Update
    fun updateCardBook(cardbook: Cardbook)

    @Query("SELECT * FROM cardbook WHERE idx = :key")
    fun getCardbook(key: Long): Cardbook?
}