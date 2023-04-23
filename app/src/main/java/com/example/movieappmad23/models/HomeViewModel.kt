package com.example.movieappmad23.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository): ViewModel() {
    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect{ movieList ->
                _movieList.value = movieList
            }
        }
    }

    suspend fun changeFavoredOfMovie(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        repository.upd(movie)
    }
}