package com.example.appsimpson001.data.model

import com.squareup.moshi.Json

data class CharacterDto(
    val id: Int,
    val name: String,
    val occupation: String? = null,
    val age: Int? = null,
    val status: String? = null,
    @Json(name = "portrait_path") val portraitPath: String? = null
)