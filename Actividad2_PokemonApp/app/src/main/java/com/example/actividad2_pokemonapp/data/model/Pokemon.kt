package com.example.actividad2_pokemonapp.data.model

import com.google.gson.annotations.SerializedName

// Modelo principal para un Pokémon con todos sus datos
data class Pokemon(
    val id: Int,                // ID único del Pokémon (ej: 25 para Pikachu)
    val name: String,           // Nombre del Pokémon (ej: "pikachu")
    val height: Int,            // Altura en decímetros (10 = 1 metro)
    val weight: Int,            // Peso en hectogramos (10 = 1 kg)
    val sprites: Sprites,       // Objeto que contiene las URLs de las imágenes
    val types: List<TypeSlot>   // Lista de tipos del Pokémon (ej: ["electric"])
)

// Modelo para las imágenes del Pokémon
data class Sprites(
    val front_default: String   // URL de la imagen frontal por defecto
)

// Modelo para el tipo del Pokémon
data class TypeSlot(
    val type: Type              // Información del tipo
)

// Información detallada del tipo
data class Type(
    val name: String            // Nombre del tipo (ej: "electric", "water")
)