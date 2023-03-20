package com.example.moviemanager.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.moviemanager.models.Movie
import com.example.moviemanager.models.getMovieById
import com.example.moviemanager.widgets.DisplayMovies

@Composable
fun FavoriteScreen(navController: NavController) {
    val movieIds = mutableListOf("tt2707408", "tt0903747")
    val movies = mutableListOf<Movie>()

    for (movieId in movieIds) {
        movies.add(getMovieById(movieId)!!)
    }

    Column {
        SimpleAppBar(title = "Favorites", navController = navController)
        DisplayMovies(movies = movies, navController = navController)
    }
}