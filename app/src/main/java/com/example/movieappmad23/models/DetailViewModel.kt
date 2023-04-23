package com.example.movieappmad23.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad23.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMovies().collect { movieList ->
                _movieList.value = movieList
            }
        }
    }

    fun getMovie(id: Int): Movie? {
        return repository.get(id)
    }

    suspend fun changeFavoredOfMovie(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        repository.upd(movie)
    }
}