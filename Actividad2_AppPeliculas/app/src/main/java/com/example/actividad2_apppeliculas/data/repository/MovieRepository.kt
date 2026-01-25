package com.example.actividad2_apppeliculas.data.repository

import com.example.actividad2_apppeliculas.data.model.Movie
import com.example.actividad2_apppeliculas.data.remote.RetrofitInstance

class MovieRepository {
    private val api = RetrofitInstance.api

    suspend fun getMovies(): List<Movie> {
        return try {
            api.getMovies()
        } catch (e: Exception) {
            emptyList()
        }
    }
}