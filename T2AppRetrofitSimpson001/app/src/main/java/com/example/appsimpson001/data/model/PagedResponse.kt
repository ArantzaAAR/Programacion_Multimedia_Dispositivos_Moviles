package com.example.appsimpson001.data.model

import com.squareup.moshi.Json

data class PagedResponse<T>(
    val count: Int,
    val next: String?,
    val prev: String?,
    val pages: Int,
    @Json(name = "results") val results: List<T>
)