package com.example.rxjavakoinpoc.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rxjavakoinpoc.movies.viewstate.MoviesInfo

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<MoviesInfo>)

    @Query("SELECT * FROM movie_table")
    fun fetchAllMovies(): LiveData<List<MoviesInfo>>

}
