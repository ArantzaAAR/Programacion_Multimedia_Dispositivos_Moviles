package com.example.appsimpson001.data.remote

import com.example.appsimpson001.data.model.CharacterDto
import com.example.appsimpson001.data.model.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpsonsApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int = 1
    ): PagedResponse<CharacterDto>
}