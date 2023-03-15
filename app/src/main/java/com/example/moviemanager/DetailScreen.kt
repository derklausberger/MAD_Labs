package com.example.moviemanager

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviemanager.models.Movie
import com.example.moviemanager.models.getMovies
import com.example.moviemanager.ui.theme.MovieRow

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    Column() {
        if (movieId != null) {
            var m: Movie = getMovies().filter { m -> movieId == m.id}[0]

            DetailTopAppDropDown(navController, m.title)

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

@Composable
fun DetailTopAppDropDown(navController: NavController, title: String) {
    TopAppBar(
        backgroundColor = Color.Blue,
        title = {
            Text(text = title, color = Color.White)
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp()}) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}

