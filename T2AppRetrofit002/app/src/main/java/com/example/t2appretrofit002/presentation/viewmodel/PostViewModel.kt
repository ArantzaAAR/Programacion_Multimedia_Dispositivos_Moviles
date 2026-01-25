package com.example.t2appretrofit002.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t2appretrofit002.data.model.Post
import com.example.t2appretrofit002.data.repository.PostRepository
import kotlinx.coroutines.launch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class PostViewModel : ViewModel() {

    private val repository = PostRepository()

    var postList by mutableStateOf<List<Post>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                postList = repository.getPosts()
            } catch (e: Exception) {
                // Log error o manejar el estado
            } finally {
                isLoading = false
            }
        }
    }
}