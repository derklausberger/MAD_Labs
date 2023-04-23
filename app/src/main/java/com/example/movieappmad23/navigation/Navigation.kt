package com.example.movieappmad23.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad23.MoviesViewModelFactory
import com.example.movieappmad23.data.MovieDatabase
import com.example.movieappmad23.models.*
import com.example.movieappmad23.repositories.MovieRepository
import com.example.movieappmad23.screens.*

@Composable
fun Navigation() {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao())
    val factory = MoviesViewModelFactory(repository)

    val navController = rememberNavController()

    val homeMoviesViewModel: HomeViewModel = viewModel(factory = factory)
    val addMovieViewModel: AddMovieViewModel = viewModel(factory = factory)
    val detailViewModel: DetailViewModel = viewModel(factory = factory)
    val favoriteViewModel: FavoriteViewModel = viewModel(factory = factory)

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            HomeScreen(
                navController = navController,
                moviesViewModel = homeMoviesViewModel
            )
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(
                navController = navController,
                moviesViewModel = favoriteViewModel
            )
        }

        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(
                navController = navController,
                moviesViewModel = addMovieViewModel
            )
        }

        // build a route like: root/detail-screen/id=34
        composable(
            Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->    // backstack contains all information from navhost
            DetailScreen(
                navController = navController, backStackEntry.arguments?.getString(
                    DETAIL_ARGUMENT_KEY
                ),
                moviesViewModel = detailViewModel
            )   // get the argument from navhost that will be passed
        }
    }
}