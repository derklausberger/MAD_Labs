package com.example.moviemanager

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.moviemanager.models.getMovies

@Composable
fun MainScreen() {
    Column {
        HomeAppBar()
        DisplayMovies(movies = getMovies())
    }
}

@Composable
fun HomeAppBar() {
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