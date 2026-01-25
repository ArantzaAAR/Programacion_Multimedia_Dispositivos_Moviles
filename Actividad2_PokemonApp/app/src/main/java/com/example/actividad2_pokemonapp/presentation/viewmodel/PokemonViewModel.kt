package com.example.actividad2_pokemonapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actividad2_pokemonapp.data.model.Pokemon
import com.example.actividad2_pokemonapp.data.model.PokemonListItem
import com.example.actividad2_pokemonapp.data.repository.PokemonRepository
import kotlinx.coroutines.launch


// ViewModel que maneja el estado de la UI y la lógica de negocio
class PokemonViewModel : ViewModel() {

    private val repository = PokemonRepository()

    // Lista de Pokémon básicos
    var pokemonList by mutableStateOf<List<PokemonListItem>>(emptyList())
        private set

    // Pokémon seleccionado (para ver detalles)
    var selectedPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        fetchPokemonList()
    }

    // Obtener lista de Pokémon
    private fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                isLoading = true
                pokemonList = repository.getPokemonList()

                // Opcional: cargar el primer Pokémon automáticamente
                if (pokemonList.isNotEmpty()) {
                    loadPokemonDetails(pokemonList.first().url)
                }

            } catch (e: Exception) {
                // Manejo de error
            } finally {
                isLoading = false
            }
        }
    }

    // Cargar detalles de un Pokémon por URL
    fun loadPokemonDetails(url: String) {
        viewModelScope.launch {
            try {
                selectedPokemon = repository.getPokemonByUrl(url)
            } catch (e: Exception) {
                // Manejo de error
            }
        }
    }

    // Cargar detalles por ID (para mantener compatibilidad)
    fun loadPokemonById(id: Int) {
        viewModelScope.launch {
            try {
                selectedPokemon = repository.getPokemonById(id)
            } catch (e: Exception) {
                // Manejo de error
            }
        }
    }
}