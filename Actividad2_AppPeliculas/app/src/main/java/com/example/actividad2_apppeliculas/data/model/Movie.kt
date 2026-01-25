package com.example.actividad2_apppeliculas.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,  // Título en inglés

    @SerializedName("original_title")
    val originalTitle: String,  // Título original en japonés

    @SerializedName("image")
    val image: String,  // URL del poster

    @SerializedName("director")
    val director: String,

    @SerializedName("release_date")
    val releaseDate: String,// Año

    @SerializedName("running_time")
    val runningTime: String, // Duración
)