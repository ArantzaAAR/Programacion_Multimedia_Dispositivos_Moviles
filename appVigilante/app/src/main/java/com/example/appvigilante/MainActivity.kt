package com.example.appvigilante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appvigilante.ui.theme.AppVigilanteTheme

// Actividad principal de Android
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Extiende la app hasta los bordes de la pantalla
        setContent { // Define el contenido usando Jetpack Compose
            AppVigilanteTheme { // Aplica el tema personalizado de la app
                MyApp() // Llama al componente principal
            }
        }
    }
}

// Anotación para usar APIs experimentales de Material3
@OptIn(ExperimentalMaterial3Api::class)
@Preview // Permite previsualizar en Android Studio
@Composable
fun MyApp() {
    // Estado reactivo para almacenar el texto de la edad
    // rememberSaveable preserva el estado durante cambios de configuración (como rotación)
    var edadTexto by rememberSaveable {
        mutableStateOf("") // Valor inicial: string vacío
    }

    var mensaje by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    // Scaffold proporciona una estructura básica de layout con AppBar y contenido
    Scaffold(
        topBar = {
            // Barra superior de la aplicación
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Puerta de acceso") // Título de la app
                }
            )
        }
    ) { innerPadding -> // innerPadding proporciona el espacio necesario para no solapar con la TopAppBar

        // Surface es un contenedor con sombra y forma definida
        Surface(
            modifier = Modifier
                .padding(innerPadding) // Aplica el padding del Scaffold
                .fillMaxSize(), // Ocupa todo el espacio disponible
            shape = MaterialTheme.shapes.large, // Forma con bordes redondeados
            color = Color(0xFFB1EDF5), // Color de fondo azul claro
            shadowElevation = 10.dp // Sombra para efecto de elevación
        ) {
            // Columna para organizar elementos verticalmente
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top, // Alinea elementos en la parte superior
                horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
            ) {
                // Texto de bienvenida
                Text(
                    modifier = Modifier.padding(8.dp), // Espacio interno alrededor del texto
                    text = "Bienvenidos al evento del año",
                    style = TextStyle(
                        color = Color.White, // Color del texto
                        fontSize = 26.sp // Tamaño de fuente
                    )
                )

                // Espacio vertical de 24dp para separar elementos
                Spacer(Modifier.height(24.dp))

                                // Campo de texto para introducir la edad
                TextField(
                    value = edadTexto, // Valor actual del campo (conectado al estado)
                    onValueChange = {edadTexto = it.trim()},//edadTexto coje de la función anónima el dato y trim es par aque no deje espacios
                    label = { Text("Edad...") } // Texto de etiqueta (placeholder)
                )

                // Espacio vertical de 24dp para separar elementos
                Spacer(Modifier.height(24.dp))

                Button(
                    onClick ={
                        val edad = edadTexto.toIntOrNull()
                        mensaje = when {
                            edad == null ->"Edad no válida"
                            edad < 18 -> "Eres menor de edad"
                            else ->"Puedes pasar, eres mayor de edad"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )


                ){
                    Text("Mirando el DNI")
                }

                // Espacio vertical de 24dp para separar elementos
                Spacer(Modifier.height(24.dp))
                if (mensaje != null) {
                    Text(
                        text = mensaje!!,
                        style = TextStyle(
                            color= Color.Black,
                            fontSize = 30.sp
                        )
                    )
                }
            }
        }
    }
}