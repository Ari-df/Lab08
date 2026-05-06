package com.example.lab07_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tarea::class], version = 1, exportSchema = false)
abstract class TaskMasterDatabase : RoomDatabase() {

    abstract fun tareaDao(): TareaDao

    companion object {
        @Volatile
        private var INSTANCE: TaskMasterDatabase? = null

        fun getDatabase(context: Context): TaskMasterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskMasterDatabase::class.java,
                    "taskmaster_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
