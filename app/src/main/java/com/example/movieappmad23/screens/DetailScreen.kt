package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad23.models.DetailViewModel
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.widgets.HorizontalScrollableImageView
import com.example.movieappmad23.widgets.MovieRow
import com.example.movieappmad23.widgets.SimpleTopAppBar
import kotlinx.coroutines.launch

/*fun filterMovie(movieId: String): Movie {
    return getMovies().filter { it.id == movieId }[0]
}*/

@Composable
fun DetailScreen(
    navController: NavController,
    movieId: String?,
    moviesViewModel: DetailViewModel
) {
    val coroutineScope = rememberCoroutineScope()

    // needed for show/hide snackbar
    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`

    movieId?.let {
        //val movie = moviesViewModel.getMovie(id = movieId.toInt())!!
        val movie = moviesViewModel.movieList.value.filter { it.id == movieId.toInt()  }[0]


        Scaffold(
            scaffoldState = scaffoldState, // attaching `scaffoldState` to the `Scaffold`
            topBar = {
                SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                    Text(text = movie.title)
                }
            },
        ) { padding ->
            if (movie != null) {
                MainContent(
                    modifier = Modifier.padding(padding),
                    movie = movie,
                    moviesViewModel = moviesViewModel
                )
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, movie: Movie, moviesViewModel: DetailViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val coroutineScope = rememberCoroutineScope()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            MovieRow(
                movie = movie,
                onFavoriteClick = { movie ->
                    coroutineScope.launch {
                        moviesViewModel.changeFavoredOfMovie(movie)
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Text(text = "Movie Images", style = MaterialTheme.typography.h5)

            HorizontalScrollableImageView(movie = movie)
        }
    }
}