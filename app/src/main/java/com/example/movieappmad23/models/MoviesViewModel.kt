package com.example.movieappmad23.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieList = MutableStateFlow(listOf<Movie>())
    private val _likedMovies = mutableStateListOf<Movie>()

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect{movieList ->
                _movieList.value = movieList
            }
        }
    }

    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    private val likedMovies: List<Movie>
        get() = _likedMovies

    fun changeFavoredOfMovie(id: Movie) {
    }

    fun getMovie(id: String): Movie? {
        /*val idx = movieList.indexOfFirst { it.id == id }
        val m = movieList[idx]

        return m;*/
        return null
    }
}