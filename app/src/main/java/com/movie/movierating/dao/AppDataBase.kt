package com.movie.movierating.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.movie.movierating.model.Movies

@Database(entities = arrayOf(Movies::class), version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun movieDAO(): MoviesDAO

    companion object {
        var INSTANCE: AppDataBase? = null

        fun openDataBase(context: Context): AppDataBase? {
            if(INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "movie.db")
                        .allowMainThreadQueries()
                        .build()
                }

            }
            return INSTANCE
        }

        fun closeDataBase() {
            INSTANCE = null
        }
    }

}