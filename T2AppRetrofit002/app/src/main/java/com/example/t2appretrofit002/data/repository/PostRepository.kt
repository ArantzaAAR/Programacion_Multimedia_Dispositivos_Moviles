package com.example.t2appretrofit002.data.repository

import com.example.t2appretrofit002.data.model.Post
import com.example.t2appretrofit002.data.remote.RetrofitInstance


class PostRepository {
    suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }
}