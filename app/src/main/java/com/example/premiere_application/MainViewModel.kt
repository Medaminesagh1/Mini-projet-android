package com.example.premiere_application

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query

class MainViewModel(savedStateHandle : SavedStateHandle) : ViewModel(){
    //val filmId: String? = savedStateHandle["filmId"]
    val film = MutableStateFlow(Film())
    fun film_detail(id: String) {
        viewModelScope.launch {
            film.value = service.detail_film(id, apikey)
        }
    }

    val serie = MutableStateFlow(Serie())
    fun serie_detail(id: String) {
        viewModelScope.launch {
            serie.value = service.detail_serie(id, apikey)
        }
    }

    val films = MutableStateFlow<List<Film>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val acteurs = MutableStateFlow<List<Acteur>>(listOf())

    val filmDetail = MutableStateFlow(FilmDetail())
    val serieDetail = MutableStateFlow(SerieDetail())

    val apikey = "d936676cee467fd5bde1950ab82959ee"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TMDB_API::class.java)

    // TODO : Si la liste des films est vide, le composant doit appeler la fonction
    //  getFilmsInitiaux du ViewModel (et uniquement si elle est vide, sinon à chaque fois que le
    //  composant sera rafraichi, il demandera le téléchargement de la liste, ce qui provoquera à
    //  nouveau son rafraichissement, et donc une boucle infinie).

    fun films_tendance(){
        viewModelScope.launch {
            films.value = service.derniers_films(apikey).results
        }
    }

    fun series_tendance(){
        viewModelScope.launch {
            series.value = service.dernieres_series(apikey).results
        }
    }

    fun acteurs_tendance(){
        viewModelScope.launch {
            acteurs.value = service.derniers_acteurs(apikey).results
        }
    }

    fun films_recherche(query: String){
        viewModelScope.launch {
            films.value = service.recherche_films(apikey, query).results
         }
    }

    fun series_recherche(query: String){
        viewModelScope.launch {
            series.value = service.recherche_series(apikey, query).results
        }
    }

    fun acteurs_recherche(query: String){
        viewModelScope.launch {
            acteurs.value = service.recherche_acteurs(apikey, query).results
        }
    }

    fun film_distribution(id: String){
        viewModelScope.launch {
            filmDetail.value = service.distribution_film(id, apikey)
        }
    }

    fun serie_distribution(id: String){
        viewModelScope.launch {
            serieDetail.value = service.distribution_serie(id, apikey)
        }
    }

}