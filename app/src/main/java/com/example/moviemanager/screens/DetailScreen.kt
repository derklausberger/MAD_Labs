package com.example.moviemanager.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviemanager.models.Movie
import com.example.moviemanager.models.getMovieById
import com.example.moviemanager.widgets.MovieRow

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val m: Movie? = getMovieById(movieId!!)
    if (m != null) {
        Column {
            SimpleAppBar(title = m.title, navController = navController)
            MovieRow(m = m, onItemClick = {})
            MovieImages(m = m)
        }
    }
}

@Composable
fun MovieImages(m: Movie) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            text = "Movie Images",
            fontSize = 35.sp,
        )
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .height(300.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(m.images.size) { img ->
                Card(
                    modifier = Modifier.width(900.dp)
                ) {
                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(m.images[img])
                            .crossfade(true)
                            .build()
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}