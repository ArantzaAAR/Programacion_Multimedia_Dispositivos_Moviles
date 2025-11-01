package com.example.a01_ejemplo_deepseek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiAppTheme {
                FormularioCompleto()
            }
        }
    }
}

@Composable
fun MiAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCompleto() {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contadorClicks by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario de Registro") },
                actions = {
                    IconButton(
                        onClick = {
                            println("Botón de búsqueda clickeado")
                        }
                    ) {
                        Icon(Icons.Default.Search, "Buscar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { contadorClicks++ }
            ) {
                Text("$contadorClicks")
            }
        }
    ) { innerPadding ->
        // BOX como contenedor principal con fondo degradado
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFE3F2FD))
        ) {
            // COLUMN como layout principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título con Box para fondo especial
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Blue, RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        "Completa tus datos",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ROW para campos del formulario
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // COLUMN para nombre
                    Column(modifier = Modifier.weight(1f)) {
                        TextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // COLUMN para email
                    Column(modifier = Modifier.weight(1f)) {
                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // ROW para botones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Botón Cancelar con eventos
                    Button(
                        onClick = {
                            nombre = ""
                            email = ""
                            println("Formulario cancelado")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text("Cancelar")
                    }

                    // Botón Enviar con eventos
                    Button(
                        onClick = {
                            println("Formulario enviado: $nombre, $email")
                        },
                        enabled = nombre.isNotEmpty() && email.isNotEmpty(),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        Text("Enviar")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // BOX para información adicional
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .clickable {
                            println("Información clickeada")
                        }
                ) {
                    Column {
                        Text(
                            "Información del formulario",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Nombre: $nombre")
                        Text("Email: $email")
                        Text("Clicks en FAB: $contadorClicks")
                    }
                }
            }
        }
    }
}