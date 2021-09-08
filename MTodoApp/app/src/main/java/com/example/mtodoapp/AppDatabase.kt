package com.example.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mtodoapp.DB_NAME
import com.example.mtodoapp.TodoDao
import com.example.mtodoapp.TodoModel


@Database(entities = [TodoModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time. so i found this on google
        @Volatile
        private var INSTANCE: AppDatabase? = null

        //take one parameter and give object of app databases
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE  //initially null
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}