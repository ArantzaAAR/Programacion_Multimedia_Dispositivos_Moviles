package com.example.t2appretrofit002.data.remote


import com.example.t2appretrofit002.data.model.Post
import retrofit2.http.GET

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}