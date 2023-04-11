package com.example.movieappmad23.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    private val _likedMovies = mutableStateListOf<Movie>()

    val movieList: List<Movie>
        get() = _movieList

    private val likedMovies: List<Movie>
        get() = _likedMovies

    fun changeFavoredOfMovie(id: String) {
        val idx = movieList.indexOfFirst { it.id == id }
        val m = movieList[idx]

        m.isFavorite = !m.isFavorite
        _movieList.removeAt(idx)
        _movieList.add(idx, m)

        if (m.isFavorite) _likedMovies.add(m)
        else _likedMovies.removeAt(likedMovies.indexOfFirst { it.id == id })
    }

    fun addMovie(m: Movie) {
        var idx = movieList.firstOrNull{ it.id == m.id }
        println(idx)

        while (idx != null) {
            m.id = m.id + "_"
            idx = movieList.firstOrNull{ it.id == m.id }
        }
        _movieList.add(m)
    }

    fun getMovie(id: String): Movie {
        val idx = movieList.indexOfFirst { it.id == id }
        val m = movieList[idx]

        return m;
    }
}