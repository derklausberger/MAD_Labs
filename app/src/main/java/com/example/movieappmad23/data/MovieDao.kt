package com.example.movieappmad23.data

import androidx.room.*
import com.example.movieappmad23.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun add(m: Movie)

    @Update
    suspend fun upd(m: Movie)

    @Delete
    suspend fun del(m: Movie)

    @Query("SELECT * FROM movie")
    fun readAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id=:movieId")
    fun getMovie(movieId: Int): Movie
}