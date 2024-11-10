package com.example.premiere_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.premiere_application.ui.theme.Premiere_applicationTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()
        // recupérer viewModel : voir vidéo sur Retrofit (12min)


        setContent {
            Premiere_applicationTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                //Ecran(windowSizeClass)
                // A surface container using the 'background' color from the theme

                var text by remember { mutableStateOf("") }
                var active by remember { mutableStateOf(false) }
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                var historyFilms = remember {
                    mutableStateListOf("Big")
                }
                var historyActeurs = remember {
                    mutableStateListOf("")
                }
                var historySeries = remember {
                    mutableStateListOf("")
                }
                Scaffold(
                        topBar = {
                            if(currentDestination?.route != "Profil"
                                && currentDestination?.route !="FilmDetail/{filmId}"
                                && currentDestination?.route !="SerieDetail/{serieId}") {
                                SearchBar(
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "icone search"
                                        )
                                    },
                                    trailingIcon = {
                                        Icon(
                                            modifier = Modifier.clickable {
                                                if(text.isNotEmpty()) {
                                                    text = ""
                                                }else{
                                                    active = false
                                                    /** Ca demande trop de ressources et ca crache
                                                    if(currentDestination?.route == "FilmsComposant"){
                                                        viewModel.films_tendance()
                                                    } else if(currentDestination?.route == "SeriesComposant"){
                                                        viewModel.series_tendance()
                                                    } else if(currentDestination?.route == "ActeursComposant"){
                                                        viewModel.acteurs_tendance()
                                                    }
                                                    */
                                                }
                                            },
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "icone close"
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    query = text,
                                    onQueryChange = { text = it },
                                    onSearch = {
                                        if (currentDestination?.route == "FilmsComposant") {
                                            historyFilms.add(text)
                                            viewModel.films_recherche(it)
                                            active = false
                                        } else if (currentDestination?.route == "ActeursComposant") {
                                            viewModel.acteurs_recherche(it)
                                            historyActeurs.add(text)
                                            active = false
                                        } else if (currentDestination?.route == "SeriesComposant") {
                                            viewModel.series_recherche(it)
                                            historySeries.add(text)
                                            active = false
                                        }
                                    },
                                    active = active,
                                    onActiveChange = { active = it },
                                    placeholder = {
                                        Text(text = "Search")
                                    }
                                ) {
                                    historyFilms.forEach{
                                        Row(modifier = Modifier.padding(all = 14.dp)){
                                            Icon(
                                                imageVector = Icons.Default.History,
                                                contentDescription = "icone historique",
                                                modifier = Modifier.padding(end = 10.dp)
                                            )
                                            Text(text = it /**, modifier = modifier = Modifier.clickable { text = it }*/)
                                            }
                                        }
                                    }
                                }


                        },
                        bottomBar = {
                            if(currentDestination?.route != "Profil" && currentDestination?.route !="FilmDetail") {
                            BottomNavigation() {

                                BottomNavigationItem(
                                    icon = {/**
                                        Image(
                                            painterResource(id = R.drawable.clap_bis),
                                            contentDescription = "logo films",
                                        )*/
                                        Icon(
                                            imageVector = Icons.Default.Movie,
                                            contentDescription = "icone search"
                                        )
                                    },
                                    label = { Text("Films") },
                                    selected = false,
                                    onClick = {
                                        navController.navigate("FilmsComposant")
                                    })
                                BottomNavigationItem(
                                    icon = {
                                        Image(
                                            painterResource(id = R.drawable.acteur),
                                            contentDescription = "logo acteurs",
                                        )
                                    },
                                    label = { Text("Acteurs") },
                                    selected = false,
                                    onClick = {
                                        navController.navigate("ActeursComposant")
                                    })
                                BottomNavigationItem(
                                    icon = {
                                        Image(
                                            painterResource(id = R.drawable.video),
                                            contentDescription = "logo séries",
                                        )
                                    },
                                    label = { Text("Séries") },
                                    selected = false,
                                    onClick = {
                                        navController.navigate("SeriesComposant")
                                    })

                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "Profil",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("Profil") { Ecran(windowSizeClass, navController) }
                        composable("FilmsComposant") {
                            FilmComposant(
                                classes = windowSizeClass,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable("SeriesComposant") {
                            SeriesComposant(
                                classes = windowSizeClass,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable("ActeursComposant") {
                            ActeurComposant(
                                classes = windowSizeClass,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable("FilmDetail/{filmId}") {
                            val id =it.arguments?.getString("filmId")?:""
                            FilmDetail(
                                classes = windowSizeClass,
                                navController = navController,
                                viewModel = viewModel,
                                id
                            )
                        }
                        composable("SerieDetail/{serieId}") {
                            val id =it.arguments?.getString("serieId")?:""
                            SerieDetail(
                                classes = windowSizeClass,
                                navController = navController,
                                viewModel = viewModel,
                                id
                            )
                        }
                    }
                }
            }
        }
    }
}


