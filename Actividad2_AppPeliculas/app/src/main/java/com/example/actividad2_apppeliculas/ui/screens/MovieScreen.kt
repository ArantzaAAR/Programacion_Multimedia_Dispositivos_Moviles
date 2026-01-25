package com.example.actividad2_apppeliculas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.actividad2_apppeliculas.presentation.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(viewModel: MovieViewModel = viewModel()) {
    val movies = viewModel.movieList
    val loading = viewModel.isLoading.value
    val error = viewModel.errorMessage.value

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = "Películas Studio Ghibli",
                    fontWeight = FontWeight.Bold
                )
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            if (loading) {
                // Pantalla de carga
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Cargando...")
                }
            } else if (error != null) {
                // Pantalla de error
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = error, color = Color.Red)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.fetchMovies() }) {
                        Text("Reintentar")
                    }
                }
            } else if (movies.isEmpty()) {
                // Pantalla vacía
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No hay películas")
                }
            } else {
                // Lista de películas
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(movies) { movie ->
                        MovieCardSimple(movie = movie)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCardSimple(movie: com.example.actividad2_apppeliculas.data.model.Movie) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen de la película
            AsyncImage(
                model = movie.image,
                contentDescription = movie.title,
                modifier = Modifier
                    .size(80.dp, 120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Información de la película
            Column(modifier = Modifier.weight(1f)) {
                // Título
                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Título original
                Text(
                    text = movie.originalTitle,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Director
                Text(
                    text = "Director: ${movie.director}",
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Fecha y duración en una línea
                Row {
                    Text(
                        text = "Año: ${movie.releaseDate.take(4)}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Duración: ${movie.runningTime} min",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}