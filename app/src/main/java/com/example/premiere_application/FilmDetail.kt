package com.example.premiere_application

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun FilmDetail(classes: WindowSizeClass, navController: NavController, viewModel : MainViewModel, id: String){
    val classeHauteur = classes.heightSizeClass
    LaunchedEffect(true) {
        viewModel.film_detail(id)
    }
    val filmDetail by viewModel.filmDetail.collectAsState()
    LaunchedEffect(true) {
        viewModel.film_distribution(id)
    }
    @Composable
    fun CardCast(cast: Cast, navController: NavController, modifier: Modifier) {
        MyCard(
            route = "acteurDetail/" + cast.id,
            chemin_img = cast.profile_path,
            titre = cast.name,
            date = null,
            navController = navController,
            modifier = modifier
        )
    }

    when (classeHauteur) {
        WindowHeightSizeClass.Medium -> {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 5.dp),
                        columns = GridCells.Fixed(2)
                    ) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Column() {
                                Row() {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = filmDetail.title,
                                            style = MaterialTheme.typography.headlineLarge,
                                            modifier = Modifier
                                                .padding(10.dp)
                                        )

                                        AsyncImage(
                                            model = "https://image.tmdb.org/t/p/w500${filmDetail.poster_path}",
                                            contentDescription = filmDetail.title
                                        )
                                    }
                                }
                                Row() {
                                    Column() {
                                        Text(
                                            text = "Details",
                                            style = MaterialTheme.typography.headlineSmall,
                                            modifier = Modifier
                                                .padding(10.dp)
                                        )
                                        Row {
                                            Text(
                                                text = "Date : ",
                                                style = MaterialTheme.typography.bodyMedium,
                                                modifier = Modifier
                                                    .padding(top =10.dp, start = 10.dp, bottom = 10.dp)
                                            )
                                            Text(
                                                text = filmDetail.release_date,
                                                style = MaterialTheme.typography.bodyMedium,
                                                modifier = Modifier
                                                    .padding(top =10.dp, start = 2.dp)
                                            )
                                        }
                                        Row {
                                            Text(
                                                text = "Genre(s) : ",
                                                style = MaterialTheme.typography.bodyMedium,
                                                modifier = Modifier
                                                    .padding(top =10.dp, start = 10.dp, bottom = 10.dp),
                                            )
                                                filmDetail.genres.forEach() {
                                                    Text(
                                                        text = it.name + " ",
                                                        style = MaterialTheme.typography.bodyMedium,
                                                        modifier = Modifier
                                                            .padding(top =10.dp, start = 2.dp),
                                                    )
                                                }
                                        }
                                    }
                                }
                                Row() {
                                    Text(
                                        text = "Synopsis",
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }
                                Row() {
                                    Text(
                                        text = filmDetail.overview,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }
                                Row() {
                                    Text(
                                        text = "Têtes d'affiche",
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }
                            }
                        }
                        items(filmDetail.credits.cast) { cast ->
                            CardCast(cast, navController, modifier = Modifier)
                        }
                    }
                }
            }
        }


        WindowHeightSizeClass.Compact -> {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 5.dp),
                        columns = GridCells.Fixed(3)
                    ) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Column() {
                                Row() {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Text(
                                            text = filmDetail.title,
                                            style = MaterialTheme.typography.headlineLarge,
                                            modifier = Modifier
                                                .padding(10.dp)
                                        )

                                        AsyncImage(
                                            model = "https://image.tmdb.org/t/p/w500${filmDetail.poster_path}",
                                            contentDescription = filmDetail.title
                                        )
                                    }
                                }
                                Row() {
                                    Column() {
                                        Text(
                                            text = "Details",
                                            style = MaterialTheme.typography.headlineSmall,
                                            modifier = Modifier
                                                .padding(10.dp)
                                        )
                                        Row {
                                            Text(
                                                text = "Date : ",
                                                style = MaterialTheme.typography.bodyMedium,
                                                modifier = Modifier
                                                    .padding(top =10.dp, start = 10.dp, bottom = 10.dp)
                                            )
                                            Text(
                                                text = filmDetail.release_date,
                                                style = MaterialTheme.typography.bodyMedium,
                                                modifier = Modifier
                                                    .padding(top =10.dp, start = 2.dp)
                                            )
                                        }
                                        Row {
                                            Text(
                                                text = "Genre(s) : ",
                                                style = MaterialTheme.typography.bodyMedium,
                                                modifier = Modifier
                                                    .padding(top =10.dp, start = 10.dp, bottom = 10.dp),
                                            )
                                            filmDetail.genres.forEach() {
                                                Text(
                                                    text = it.name + " ",
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    modifier = Modifier
                                                        .padding(top =10.dp, start = 2.dp),
                                                )
                                            }
                                        }
                                    }
                                }
                                Row() {
                                    Text(
                                        text = "Synopsis",
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }
                                Row() {
                                    Text(
                                        text = filmDetail.overview,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }
                                Row() {
                                    Text(
                                        text = "Têtes d'affiche",
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )
                                }
                            }
                        }
                        items(filmDetail.credits.cast) { cast ->
                            CardCast(cast, navController, modifier = Modifier)
                        }
                    }
                }
            }
        }
    }
}

