package com.example.actividad2_pokemonapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto singleton que crea y mantiene la instancia de Retrofit
object RetrofitInstance {

    // URL base de PokeAPI - siempre funciona
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    // Instancia lazy de la API (se crea solo cuando se necesita por primera vez)
    val api: PokemonApi by lazy {
        // Construimos Retrofit con la URL base y el convertidor Gson
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // Establecemos la URL base
            .addConverterFactory(GsonConverterFactory.create())  // Para convertir JSON a objetos Kotlin
            .build()
            .create(PokemonApi::class.java)  // Creamos la implementación de la interfaz
    }
}