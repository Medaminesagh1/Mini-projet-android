package com.example.premiere_application

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ActeurComposant(
    classes: WindowSizeClass,
    navController: NavController,
    viewModel: MainViewModel
) {
    val classeHauteur = classes.heightSizeClass
    val acteurs by viewModel.acteurs.collectAsState()
    LaunchedEffect(true) {
        viewModel.acteurs_tendance()
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
                        items(acteurs) { acteur ->
                            CardActeur(acteur, navController, modifier = Modifier)
                        }
                    }
                }
            }
        }
            WindowHeightSizeClass.Compact -> {
                androidx.compose.material3.Surface(
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
                            items(acteurs) { acteur ->
                                CardActeur(acteur, navController, modifier = Modifier)
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
fun CardActeur(acteur: Acteur, navController: NavController, modifier: Modifier) {
    MyCard(
        route = "acteurDetail/" + acteur.id,
        chemin_img = acteur.profile_path,
        titre = acteur.name,
        date = null,
        navController = navController,
        modifier = Modifier
    )
}