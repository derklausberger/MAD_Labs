package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.MoviesViewModel
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar

@Composable
fun FavoriteScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies")
        }
    }) { padding ->
        val movieList: List<Movie> = moviesViewModel.movieList

        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(movieList) { movie ->
                    if (movie.isFavorite) {
                        MovieRow(
                            movie = movie,
                            onItemClick = { movieId ->
                                navController.navigate(route = Screen.DetailScreen.withId(movieId))
                            },
                            onFavoriteChange = { id -> moviesViewModel.changeFavoredOfMovie(id) }
                        )
                    }
                }
            }
        }
    }
}