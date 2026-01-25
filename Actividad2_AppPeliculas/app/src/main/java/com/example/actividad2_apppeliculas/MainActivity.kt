package com.example.actividad2_apppeliculas


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.actividad2_apppeliculas.ui.screens.MovieScreen
import com.example.actividad2_apppeliculas.presentation.viewmodel.MovieViewModel
import com.example.actividad2_apppeliculas.ui.theme.Actividad2_AppPeliculasTheme

class MainActivity : ComponentActivity() {

    // Instanciamos el ViewModel correctamente
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Actividad2_AppPeliculasTheme {
                MovieScreen(viewModel = movieViewModel)
            }
        }
    }
}

