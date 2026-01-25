package com.example.appsimpson001.data.repository

import com.example.appsimpson001.data.model.CharacterDto
import com.example.appsimpson001.data.remote.SimpsonsApi

class SimpsonsRepository(
    private val api: SimpsonsApi
) {
    suspend fun getCharacters(page: Int = 1): List<CharacterDto> =
        api.getCharacters(page).results
}