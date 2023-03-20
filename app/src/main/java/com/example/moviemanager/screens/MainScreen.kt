package com.example.moviemanager.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.moviemanager.models.Screen
import com.example.moviemanager.models.getMovies
import com.example.moviemanager.widgets.DisplayMovies

@Composable
fun MainScreen(navController: NavController) {
    Column {
        HomeAppBar(navController)
        DisplayMovies(movies = getMovies(), navController = navController)
    }
}

@Composable
fun HomeAppBar(navController: NavController) {
    var expanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        backgroundColor = Color.Blue,
        title = {Text(text = "Movies", color = Color.White)},
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
            ) {
                DropdownMenuItem(
                    onClick = {
                        navController.navigate(Screen.FavoriteScreen.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null
                    )
                    Text(text = "Favorites")
                }
            }
        }
    )
}