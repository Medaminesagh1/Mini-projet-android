package com.example.premiere_application

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.LaunchedEffect
@Composable
fun compasantExam(navController: NavController, classes: WindowSizeClass, viewModel: MainViewModel){

    val classeHauteur = classes.heightSizeClass

    val examen by viewModel.examen.collectAsState()


    LaunchedEffect(true) {
        viewModel.rechercherFilmsHorreur("horror")
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
                        items(examen) { collection ->

                            Text(
                                text = collection.name,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {

                                        Log.v("Collection", collection.name)
                                    }
                            )
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
                        items(examen) { collection ->

                            Text(
                                text = collection.name,

                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {

                                        Log.v("Collection", collection.name)
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}