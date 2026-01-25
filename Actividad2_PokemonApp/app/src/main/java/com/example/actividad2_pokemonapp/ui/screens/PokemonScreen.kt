package com.example.actividad2_pokemonapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.actividad2_pokemonapp.R
import com.example.actividad2_pokemonapp.presentation.viewmodel.PokemonViewModel
import com.example.actividad2_pokemonapp.ui.theme.PokemonBlue
import com.example.actividad2_pokemonapp.ui.theme.PokemonYellow
import com.example.actividad2_pokemonapp.ui.theme.PokemonYellow

@Composable
fun PokemonScreen(viewModel: PokemonViewModel = viewModel()) {

    val pokemonList = viewModel.pokemonList
    val selectedPokemon = viewModel.selectedPokemon
    val loading = viewModel.isLoading

    Scaffold(
        topBar = {
            // Título colorido sin iconos problemáticos
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(PokemonBlue, PokemonYellow, PokemonBlue)
                        )
                    )
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Pokeball decorativa usando texto/emoji
                    Text(
                        text = "●", // Círculo rojo
                        fontSize = 24.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Texto principal POKÉDEX
                    Text(
                        text = "POKÉDEX",
                        color = Color.White,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 3.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Pokeball decorativa usando texto/emoji
                    Text(
                        text = "●", // Círculo rojo
                        fontSize = 24.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    ) { paddingValues ->
        // Fondo degradado colorido
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFA2D2FF),  // Azul claro arriba
                            Color(0xFFFFE5A8)   // Amarillo claro abajo
                        )
                    )
                )
                .padding(paddingValues)
        ) {
            if (loading) {
                LoadingScreen()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) {
                    // Pokémon seleccionado (parte superior)
                    if (selectedPokemon != null) {
                        PokemonDetailCard(
                            pokemon = selectedPokemon,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(vertical = 8.dp)
                        )
                    } else {
                        // Placeholder si no hay Pokémon seleccionado
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(vertical = 8.dp)
                                .background(
                                    color = PokemonBlue.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Selecciona un Pokémon",
                                color = PokemonBlue,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Lista de Pokémon (parte inferior)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .background(
                                color = PokemonBlue.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "MÁS POKÉMON",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            color = PokemonBlue,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                    PokemonListGrid(
                        pokemonList = pokemonList,
                        onPokemonSelected = { url ->
                            viewModel.loadPokemonDetails(url)
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonDetailCard(
    pokemon: com.example.actividad2_pokemonapp.data.model.Pokemon,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(20.dp),
                spotColor = PokemonBlue
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del Pokémon con fondo circular colorido
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(PokemonYellow, PokemonBlue)
                        ),
                        shape = CircleShape
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = pokemon.sprites.front_default,
                    contentDescription = "Imagen de ${pokemon.name}",
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            // Información del Pokémon
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Nombre e ID
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = pokemon.name.replaceFirstChar { it.uppercase() },
                        color = PokemonBlue,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .background(PokemonBlue, shape = CircleShape)
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "#${pokemon.id}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Altura y Peso
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatCard(
                        title = "ALTURA",
                        value = "${pokemon.height / 10.0} m",
                        color = PokemonBlue
                    )

                    StatCard(
                        title = "PESO",
                        value = "${pokemon.weight / 10.0} kg",
                        color = PokemonYellow
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Tipos
                Text(
                    text = "TIPOS",
                    color = PokemonBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    pokemon.types.forEach { typeSlot ->
                        val typeColor = when (typeSlot.type.name.lowercase()) {
                            "electric" -> Color(0xFFFFD700)
                            "water" -> Color(0xFF6890F0)
                            "fire" -> Color(0xFFF08030)
                            "grass" -> Color(0xFF78C850)
                            "psychic" -> Color(0xFFF85888)
                            "poison" -> Color(0xFFA040A0)
                            "ground" -> Color(0xFFE0C068)
                            "flying" -> Color(0xFFA890F0)
                            else -> PokemonBlue
                        }

                        Box(
                            modifier = Modifier
                                .background(typeColor, shape = RoundedCornerShape(12.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = typeSlot.type.name.replaceFirstChar { it.uppercase() },
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(title: String, value: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .background(color.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = value,
                color = color,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun PokemonListGrid(
    pokemonList: List<com.example.actividad2_pokemonapp.data.model.PokemonListItem>,
    onPokemonSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Dividir la lista en grupos de 2 para hacer grid
        val chunkedList = pokemonList.chunked(2)

        items(chunkedList) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { pokemonItem ->
                    PokemonGridItem(
                        pokemonItem = pokemonItem,
                        onSelected = { onPokemonSelected(pokemonItem.url) },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Si la fila tiene solo 1 item, añadir espacio vacío
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun PokemonGridItem(
    pokemonItem: com.example.actividad2_pokemonapp.data.model.PokemonListItem,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pokemonId = pokemonItem.url
        .removeSuffix("/")
        .substringAfterLast("/")
        .toIntOrNull() ?: 0

    Card(
        modifier = modifier
            .clickable { onSelected() }
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = PokemonBlue.copy(alpha = 0.3f)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Fondo circular para la imagen
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(PokemonBlue.copy(alpha = 0.2f), Color.Transparent)
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (pokemonId > 0) {
                    AsyncImage(
                        model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png",
                        contentDescription = "Imagen de ${pokemonItem.name}",
                        modifier = Modifier.size(70.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ID pequeño
            Text(
                text = "#$pokemonId",
                color = PokemonBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            // Nombre del Pokémon
            Text(
                text = pokemonItem.name.replaceFirstChar { it.uppercase() },
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Pokébola hecha con composables (sin depender de recursos)
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(Color.White)
                )

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(PokemonBlue, shape = CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Cargando Pokédex...",
                color = PokemonBlue,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CircularProgressIndicator(
                color = PokemonBlue,
                strokeWidth = 4.dp,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}