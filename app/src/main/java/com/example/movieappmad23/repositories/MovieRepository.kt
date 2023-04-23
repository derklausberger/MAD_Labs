package com.example.movieappmad23.repositories

import com.example.movieappmad23.data.MovieDao
import com.example.movieappmad23.models.Movie

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun add(m: Movie) = movieDao.add(m)

    suspend fun del(m: Movie) = movieDao.del(m)

    suspend fun upd(m: Movie) = movieDao.upd(m)

    fun getAllMovies() = movieDao.readAll()

    fun get(id: Int) = movieDao.getMovie(id)
}