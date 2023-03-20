package com.example.moviemanager.models

const val DETAIL_ARGUMENT_KEY = "movieID"

sealed class Screen(val route: String) {
    object MainScreen : Screen(route = "main-screen")
    object DetailScreen : Screen(route = "detail-screen/{$DETAIL_ARGUMENT_KEY}") {
        fun setArgs(movieId: String): String {
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}",
                newValue = movieId)
        }
    }
    object FavoriteScreen : Screen(route = "favorite-screen")
}