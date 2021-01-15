package com.lwj.memorizer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lwj.memorizer.data.dao.CardbookDao
import com.lwj.memorizer.data.entities.Cardbook


@Database(entities = [Cardbook::class], version = 1, exportSchema = false)
abstract class CardbookDatabase : RoomDatabase() {

    abstract val cardbookDao: CardbookDao

    companion object {

        @Volatile
        private var INSTANCE: CardbookDatabase? = null

        fun getInstance(context: Context): CardbookDatabase {
            synchronized(this) {
               var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CardbookDatabase::class.java,
                        ""
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}