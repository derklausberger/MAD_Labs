package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ListItemSelectable
import com.example.movieappmad23.models.Movie
import com.example.movieappmad23.models.MoviesViewModel
import com.example.movieappmad23.widgets.SimpleTopAppBar
import java.lang.Double
import java.lang.Float

@Composable
fun AddMovieScreen(navController: NavController, moviesViewModel: MoviesViewModel) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_movie))
            }
        },
    ) { padding ->
        MainContent(navController,
            Modifier.padding(padding),
            addMovie = { m -> moviesViewModel.addMovie(m) })
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    navController: NavController, modifier: Modifier = Modifier, addMovie: (Movie) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            var title by remember {
                mutableStateOf("")
            }

            var year by remember {
                mutableStateOf("")
            }

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(genres.map { genre ->
                    ListItemSelectable(
                        title = genre.toString(), isSelected = false
                    )
                })
            }

            var director by remember {
                mutableStateOf("")
            }

            var actors by remember {
                mutableStateOf("")
            }

            var plot by remember {
                mutableStateOf("")
            }

            var rating by remember {
                mutableStateOf("")
            }

            var isEnabledSaveButton by remember {
                mutableStateOf(false)
            }

            OutlinedTextField(
                value = title,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { title = it },
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = title.isEmpty(),
                trailingIcon = {
                    if (title.isEmpty())
                        Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (title.isEmpty()) {
                Text(
                    text = "field cannot be empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(
                value = year,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { year = it },
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = year.isEmpty(),
                trailingIcon = {
                    if (year.isEmpty())
                        Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (year.isEmpty()) {
                Text(
                    text = "field cannot be empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6
            )

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp), rows = GridCells.Fixed(3)
            ) {
                items(genreItems) { genreItem ->
                    Chip(modifier = Modifier.padding(2.dp), colors = ChipDefaults.chipColors(
                        backgroundColor = if (genreItem.isSelected) colorResource(id = R.color.purple_200)
                        else colorResource(id = R.color.white)
                    ), onClick = {
                        genreItems = genreItems.map {
                            if (it.title == genreItem.title) {
                                genreItem.copy(isSelected = !genreItem.isSelected)
                            } else {
                                it
                            }
                        }
                    }) {
                        Text(text = genreItem.title)
                    }
                }
            }
            if (!genreItems.any { it.isSelected }) {
                Text(
                    text = "at least one genre must be selected",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(
                value = director,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { director = it },
                label = { Text(stringResource(R.string.enter_director)) },
                isError = director.isEmpty(),
                trailingIcon = {
                    if (director.isEmpty())
                        Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (director.isEmpty()) {
                Text(
                    text = "field cannot be empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(value = actors,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { actors = it },
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = actors.isEmpty(),
                trailingIcon = {
                    if (actors.isEmpty())
                        Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (actors.isEmpty()) {
                Text(
                    text = "field cannot be empty",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            OutlinedTextField(
                value = plot,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { plot = it },
                label = {
                    Text(
                        textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)
                    )
                },
                isError = false
            )

            OutlinedTextField(
                value = rating,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    rating = if (it.startsWith("0")) {
                        ""
                    } else {
                        it
                    }
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = !rating.matches(("\\d.?\\d?\\d?").toRegex()),
                trailingIcon = {
                    if (rating.isEmpty())
                        Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colors.error)
                }
            )
            if (!rating.matches(("\\d.?\\d?\\d?").toRegex())) {
                Text(
                    text = "field must be a number with a maximum of decimal places",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp)
                )
            }

            Button(enabled = isEnabledSaveButton, onClick = {
                var m = Movie(
                    id = title.replace(" ", ""),
                    title = title,
                    year = year,
                    genre = genres.filter {genre ->
                        genreItems.filter { selected -> selected.isSelected }
                            .map { string -> string.title }.contains(genre.name)
                    },
                    director = director,
                    actors = actors,
                    plot = plot,
                    images = listOf(
                        "https://thumbs.dreamstime.com/b/happy-cat-closeup-portrait-funny-smile-cardboard-young-blue-background-102078702.jpg"
                    ),
                    rating = rating.replace(",",".").toFloat()
                )

                addMovie(m)
                navController.navigateUp()
            }) {
                Text(text = stringResource(R.string.add))
            }

            isEnabledSaveButton =
                (title.isNotEmpty()
                        && year.isNotEmpty()
                        && genreItems.any { it.isSelected }
                        && director.isNotEmpty()
                        && actors.isNotEmpty()
                        && rating.matches(("\\d.?\\d?\\d?").toRegex()
                ))
        }
    }
}