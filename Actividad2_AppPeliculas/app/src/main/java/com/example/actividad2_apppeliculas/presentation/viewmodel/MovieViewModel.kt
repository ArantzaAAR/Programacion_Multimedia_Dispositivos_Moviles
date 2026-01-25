package com.example.actividad2_apppeliculas.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actividad2_apppeliculas.data.model.Movie
import com.example.actividad2_apppeliculas.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()

    // Estados observables
    val movieList = mutableStateListOf<Movie>()
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null

            try {
                val movies = repository.getMovies()
                movieList.clear()
                movieList.addAll(movies)

                if (movies.isEmpty()) {
                    errorMessage.value = "No se encontraron películas"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error al cargar películas: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}