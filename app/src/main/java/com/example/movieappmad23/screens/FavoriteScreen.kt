package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad23.models.FavoriteViewModel
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController, moviesViewModel: FavoriteViewModel) {
    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "My Favorite Movies")
        }
    }) { padding ->
        val movieList by moviesViewModel.movieList.collectAsState()
        val coroutineScope = rememberCoroutineScope()

        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(movieList) { movie ->
                    if (movie.isFavorite) {
                        MovieRow(
                            movie = movie,
                            onItemClick = { movieId ->
                                navController.navigate(route = Screen.DetailScreen.withId(movieId))
                            },
                            onFavoriteClick = { movie ->
                                coroutineScope.launch {
                                    moviesViewModel.changeFavoredOfMovie(movie)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}