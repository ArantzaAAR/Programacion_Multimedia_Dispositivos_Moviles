package com.example.actividad2_pokemonapp.data.repository

import com.example.actividad2_pokemonapp.data.model.Pokemon
import com.example.actividad2_pokemonapp.data.model.PokemonListItem
import com.example.actividad2_pokemonapp.data.remote.RetrofitInstance

// Clase que maneja la obtención de datos, actúa como intermediario entre ViewModel y API
class PokemonRepository {

    // Obtiene lista de Pokémon básicos
    suspend fun getPokemonList(): List<PokemonListItem> {
        val response = RetrofitInstance.api.getPokemonList(limit = 20)
        return response.results
    }

    // Obtiene detalles de un Pokémon por ID (ya lo tenías)
    suspend fun getPokemonById(id: Int): Pokemon {
        return RetrofitInstance.api.getPokemonById(id)
    }

    // Obtiene detalles de un Pokémon por su URL
    suspend fun getPokemonByUrl(url: String): Pokemon {
        // Extraer ID de la URL
        val id = url
            .removeSuffix("/")
            .substringAfterLast("/")
            .toInt()

        return getPokemonById(id)
    }
}