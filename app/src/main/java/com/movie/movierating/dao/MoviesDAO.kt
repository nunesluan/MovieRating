package com.movie.movierating.dao

import androidx.room.*
import com.movie.movierating.model.Movies


@Dao
interface MoviesDAO {

    @Query("SELECT * FROM movies")
    fun fetch(): List<Movies>

    @Insert
    fun store(vararg movies: Movies)

    @Delete
    fun delete(movie: Movies)

    @Update
    fun update(movie: Movies)

}