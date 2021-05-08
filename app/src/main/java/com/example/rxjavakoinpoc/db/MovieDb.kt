package com.example.rxjavakoinpoc.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rxjavakoinpoc.movies.viewstate.MoviesInfo

@Database(entities = [MoviesInfo::class], version = 1, exportSchema = false)
abstract class MovieDb : RoomDatabase() {

    companion object {
        fun create(context: Context): MovieDb {
            val databaseBuilder = Room.databaseBuilder(context, MovieDb::class.java, "movies.db")
            return databaseBuilder.build()
        }
    }

    abstract fun moviesDao(): MovieDao
}