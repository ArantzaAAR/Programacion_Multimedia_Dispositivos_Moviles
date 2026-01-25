package com.example.appsimpson001.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsimpson001.data.model.CharacterDto
import com.example.appsimpson001.data.remote.SimpsonsNetwork
import com.example.appsimpson001.data.repository.SimpsonsRepository
import kotlinx.coroutines.launch

class SimpsonsViewModel : ViewModel() {

    private val repository = SimpsonsRepository(SimpsonsNetwork.api)

    var state by mutableStateOf<UiState<List<CharacterDto>>>(UiState.Loading)
        private set

    init {
        loadCharacters()
    }

    fun loadCharacters(page: Int = 1) {
        viewModelScope.launch {
            state = UiState.Loading
            state = try {
                UiState.Success(repository.getCharacters(page))
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Error cargando personajes")
            }
        }
    }
}