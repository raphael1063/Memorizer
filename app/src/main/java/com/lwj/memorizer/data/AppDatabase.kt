package com.lwj.memorizer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lwj.memorizer.data.dao.CardbookDao
import com.lwj.memorizer.data.entities.Cardbook


@Database(entities = [Cardbook::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val cardbookDao: CardbookDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
//            synchronized(this) {
//               var instance = INSTANCE
//                if(instance == null) {
//                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "room_db")
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "room_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}