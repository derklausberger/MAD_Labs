package com.example.moviemanager.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviemanager.TopAppDropDown
import com.example.moviemanager.models.Movie
import com.example.moviemanager.models.getMovies

@Composable
fun HomeScreen(navController: NavController) {
    val movies = getMovies()
    Column {
        TopAppDropDown()

        LazyColumn {
            items(movies) { movie ->
                MovieRow(m = movie) {movieId ->
                    navController.navigate(route = "detail-screen/$movieId")
                }
            }
        }
    }
}

@Composable
fun OutputMovieDetails(m: Movie) {
    Column(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Year: ${m.year}",
            fontSize = 20.sp
        )
        Text(
            text = "Genre: ${m.genre}",
            fontSize = 20.sp
        )
        Text(
            text = "Director: ${m.director}",
            fontSize = 20.sp
        )
        Text(
            text = "Actors: ${m.actors}",
            fontSize = 20.sp
        )
        Text(
            text = "Plot: ${m.plot}",
            fontSize = 20.sp
        )
        Text(
            text = "Rating: ${m.rating} / 10",
            fontSize = 20.sp
        )
    }
}

@Composable
fun MovieRow(m: Movie, onItemClick: (String) -> Unit) {
    Card (
        modifier = Modifier
            .padding(5.dp)
            .clickable { onItemClick(m.id) },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.LightGray
    ) {
        var arrowUp by remember {
            mutableStateOf(true)
        }

        Column(
            modifier = Modifier.padding(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(m.images[0])
                        .crossfade(true)
                        .build())
                Image(
                    painter = painter,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                        .align(Alignment.TopEnd),
                    tint = Color.LightGray
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = m.title,
                    fontSize = 25.sp
                )

                IconButton(
                    onClick = {arrowUp = !arrowUp},
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = (if (arrowUp) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown),
                        contentDescription = null,
                    )
                }
            }

            if (!arrowUp) OutputMovieDetails(m)
        }
    }
}