package com.example.moviemanager

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.moviemanager.models.Movie

@Composable
fun DisplayMovies(movies: List<Movie>) {
    Column {
        LazyColumn {
            items(movies) { movie ->
                MovieRow(m = movie) {
                }
            }
        }
    }
}

@Composable
fun MovieRow(m: Movie, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable { onItemClick(m.id) }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.LightGray
    ) {
        var arrowUp by remember {
            mutableStateOf(true)
        }
        val rotState by animateFloatAsState(
            targetValue = if (arrowUp) 180f else 0f
        )

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
                        .data(m.images[0]).crossfade(true)
                        .build()
                )
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
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = m.title, fontSize = 25.sp
                )

                IconButton(
                    onClick = { arrowUp = !arrowUp },
                    modifier = Modifier
                        .size(30.dp)
                        .rotate(rotState)
                ) {
                    Icon(
                        imageVector = (
                                //if (arrowUp) Icons.Default.KeyboardArrowUp
                                /*else*/ Icons.Default.KeyboardArrowDown),
                        contentDescription = null,
                    )
                }
            }

            if (!arrowUp) MovieDetails(m)
        }
    }
}

@Composable
fun MovieDetails(m: Movie) {
    Column(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Year: ${m.year}", fontSize = 20.sp)
        Text(text = "Genre: ${m.genre}", fontSize = 20.sp)
        Text(text = "Director: ${m.director}", fontSize = 20.sp)
        Text(text = "Actors: ${m.actors}", fontSize = 20.sp)
        Text(text = "Rating: ${m.rating} / 10", fontSize = 20.sp)
        Divider()
        Text(text = "Plot: ${m.plot}", fontSize = 20.sp)
    }
}