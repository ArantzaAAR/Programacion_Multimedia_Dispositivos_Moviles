
package com.example.apppulsarcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme

// Actividad principal de Android
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Extiende la app hasta los bordes de la pantalla
        setContent { // Define el contenido de la actividad usando Compose
            MaterialTheme { // Aplica el tema personalizado de la app
                MyApp() // Llama al componente principal
            }
        }
    }
}

// Previsualización en Android Studio
@Preview(showBackground = true)
@Composable
fun MyApp() {
    // Estado reactivo que guarda el dinero acumulado
    // 'remember' hace que el valor persista durante recomposiciones
    // 'mutableStateOf' hace que el valor sea observable para recomponer cuando cambie
    var acumuladorDinero by remember {
        mutableStateOf(0) // Valor inicial: 0
    }

    // Surface es un contenedor básico que proporciona fondo y forma
    Surface(
        modifier = Modifier // Modificador para ajustar diseño y apariencia
            .fillMaxSize() // Ocupa toda la pantalla disponible
            .padding(all = 16.dp), // Espacio interno de 16dp en todos los lados
        color = Color(0xFF76E5F3) // Color de fondo azul claro (formato ARGB)
    ) {
        // Columna que organiza los elementos verticalmente
        Column(
            modifier = Modifier.fillMaxSize(), // Ocupa todo el espacio del Surface
            verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
        ) {
            // Texto que muestra el dinero acumulado
            Text(
                text = "Dinero: ${acumuladorDinero}€", // Texto dinámico con el valor actual
                style = TextStyle(
                    color = Color.White, // Color del texto
                    fontSize = 35.sp // Tamaño de fuente: 35 scaled pixels
                )
            )

            // Espacio vacío de 100dp de altura para separar elementos
            Spacer(Modifier.height(100.dp))

            // Botón circular personalizado
            BotonCircular(click = {
                // Acción que se ejecuta al hacer click:
                acumuladorDinero++ // Incrementa el contador en 1
                // Registro en Logcat para debugging
                Log.d("Click", "Valor de acumulador: ${acumuladorDinero}")
            })
        }
    }
}

// Componente personalizado que representa un botón circular
@Composable
fun BotonCircular(click: () -> Unit) { // Recibe una función lambda como parámetro
    // Card con forma circular que actúa como botón
    Card(
        modifier = Modifier.size(100.dp), // Tamaño fijo: 100x100 dp
        shape = CircleShape, // Forma circular
        onClick = { // Acción al hacer click
            Log.d("Click", "Botón pulsado") // Log informativo
            click() // Ejecuta la función recibida como parámetro
        },
        colors = CardDefaults.cardColors(containerColor = Color.White) // Color blanco del botón
    ) {
        // Box es un contenedor que permite superponer elementos
        Box(
            modifier = Modifier.fillMaxSize(), // Ocupa todo el espacio del Card
            contentAlignment = Alignment.Center // Centra el contenido internamente
        ) {
            Text("pulsar") // Texto dentro del botón
        }
    }
}


