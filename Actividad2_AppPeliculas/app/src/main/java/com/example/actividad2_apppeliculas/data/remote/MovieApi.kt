package com.example.actividad2_apppeliculas.data.remote

import com.example.actividad2_apppeliculas.data.model.Movie
import retrofit2.http.GET

interface MovieApi {
    @GET("films")
    suspend fun getMovies(): List<Movie>
}