package com.example.actividad2_pokemonapp.data.remote

import com.example.actividad2_pokemonapp.data.model.Pokemon
import com.example.actividad2_pokemonapp.data.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    // Obtiene lista de Pokémon (paginada)
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,   // Cuántos Pokémon por página
        @Query("offset") offset: Int = 0    // Desde qué posición empezar
    ): PokemonListResponse

    // Obtiene un Pokémon específico por su ID (ya lo tenías)
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): Pokemon
}
