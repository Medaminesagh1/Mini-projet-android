package com.example.premiere_application

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDB_API {

    @GET("trending/movie/week")
    suspend fun derniers_films(@Query("api_key") api_key: String): Films

    @GET("trending/tv/week")
    suspend fun dernieres_series(@Query("api_key") api_key: String): Series

    @GET("trending/person/week")
    suspend fun derniers_acteurs(@Query("api_key") api_key: String): Acteurs

    @GET("search/movie?")
    suspend fun recherche_films(@Query("api_key") api_key: String, @Query("query") query:String): Films

    @GET("search/tv?")
    suspend fun recherche_series(@Query("api_key") api_key: String, @Query("query") query:String): Series

    @GET("search/person?")
    suspend fun recherche_acteurs(@Query("api_key") api_key: String, @Query("query") query:String): Acteurs

    @GET("movie/{filmId}?language=fr")
    suspend fun detail_film(@Path("filmId") filmId : String?, @Query("api_key") api_key: String): Film

    @GET("tv/{serieId}")
    suspend fun detail_serie(@Path("serieId") filmId : String?, @Query("api_key") api_key: String): Serie

    @GET("movie/{filmId}?append_to_response=credits&language=fr")
    suspend fun distribution_film(@Path("filmId") filmId : String?, @Query("api_key") api_key: String): FilmDetail

    @GET("tv/{serieId}?append_to_response=credits&language=fr")
    suspend fun distribution_serie(@Path("serieId") serieId : String?, @Query("api_key") api_key: String): SerieDetail

}