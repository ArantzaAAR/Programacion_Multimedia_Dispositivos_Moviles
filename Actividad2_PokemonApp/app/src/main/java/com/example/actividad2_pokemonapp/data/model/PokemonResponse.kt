package com.example.actividad2_pokemonapp.data.model

// Respuesta de la lista de Pokémon (endpoint: /pokemon)
data class PokemonListResponse(
    val count: Int,            // Total de Pokémon disponibles
    val next: String?,         // URL de la siguiente página
    val previous: String?,     // URL de la página anterior
    val results: List<PokemonListItem>  // Lista de Pokémon básicos
)

// Pokémon básico para la lista (solo nombre y URL)
data class PokemonListItem(
    val name: String,          // Nombre del Pokémon
    val url: String            // URL para obtener detalles completos
)
