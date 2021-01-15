package com.lwj.memorizer.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lwj.memorizer.data.dao.CardbookDao
import com.lwj.memorizer.data.entities.Cardbook


@Database(entities = [Cardbook::class], version = 1)
abstract class CardbookDatabase : RoomDatabase() {

    abstract val cardbookDao: CardbookDao

}