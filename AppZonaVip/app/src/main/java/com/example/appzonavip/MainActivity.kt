package com.example.appzonavip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appzonavip.ui.theme.AppZonaVipTheme

// Actividad principal de Android - punto de entrada de la aplicación
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Extiende el contenido hasta los bordes de la pantalla
        setContent { // Define la interfaz de usuario con Jetpack Compose
            AppZonaVipTheme { // Aplica el tema personalizado de la aplicación
                MyApp() // Llama al componente principal de la app
            }
        }
    }
}

// Anotación para usar APIs experimentales de Material3
@OptIn(ExperimentalMaterial3Api::class)
@Preview // Permite previsualizar el componente en Android Studio
@Composable
fun MyApp() {
    // Estado reactivo que controla si el usuario quiere entrar a la zona VIP
    // rememberSaveable preserva el estado durante cambios de configuración (como rotar pantalla)
    var quieresEntrar by rememberSaveable {
        mutableStateOf(false) // Valor inicial: false (no quiere entrar)
    }

    // Estado reactivo para el nivel de ritmo (0-10)
    var ritmo by rememberSaveable {
        mutableStateOf(5f) // Valor inicial: 5 (float para el Slider)
    }

    // Estado reactivo para el mensaje de resultado (puede ser null inicialmente)
    var mensaje by rememberSaveable {
        mutableStateOf<String?>(null) // Valor inicial: null (sin mensaje)
    }

    // Scaffold proporciona una estructura básica de layout con AppBars
    Scaffold(
        topBar = {
            // Barra superior de la aplicación
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center, // Centra el texto horizontalmente
                        text = "ZONA VIP" // Título de la aplicación
                    )
                }
            )
        },
        bottomBar = {
            // Barra inferior de la aplicación
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "YA ESTÁS DENTRO DEL EVENTO", // Mensaje fijo en la barra inferior
                )
            }
        }
    ) { innerPadding -> // Padding interno que Scaffold proporciona para evitar solapamiento con las barras

        // Columna principal que organiza todos los elementos verticalmente
        Column(
            modifier = Modifier
                .padding(innerPadding) // Aplica el padding del Scaffold
                .padding(16.dp) // Añade padding adicional de 16dp
                .fillMaxSize(), // Ocupa todo el espacio disponible
            verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
        ) {
            // Título principal de la pantalla
            Text(
                modifier = Modifier
                    .padding(8.dp) // Espacio interno alrededor del texto
                    .fillMaxWidth(), // Ocupa todo el ancho disponible
                textAlign = TextAlign.Center, // Centra el texto
                fontSize = 22.sp, // Tamaño de fuente grande
                fontWeight = FontWeight.SemiBold, // Negrita semi-bold
                text = "Mirando la zona VIP" // CORRECCIÓN: "la" en lugar de "al"
            )

            // Espacio vertical de separación
            Spacer(Modifier.height(16.dp))

            // Fila para el interruptor de entrada a la zona VIP
            Row(
                verticalAlignment = Alignment.CenterVertically // Alinea los elementos al centro vertical
            ) {
                Text("¿Quieres entrar en la zona VIP?") // Texto de la pregunta
                Spacer(Modifier.width(8.dp)) // Espacio horizontal entre texto e interruptor
                Switch(
                    checked = quieresEntrar, // Estado actual del interruptor
                    onCheckedChange = { nuevoEstado ->
                        quieresEntrar = nuevoEstado // Actualiza el estado cuando cambia
                    }
                )
            }

            // Espacio vertical de separación
            Spacer(Modifier.height(16.dp))

            // Texto que muestra el nivel de ritmo actual (convertido a entero)
            Text(text = "Nivel de ritmo: ${ritmo.toInt()}")

            // Espacio vertical de separación
            Spacer(Modifier.height(16.dp))

            // Control deslizante para ajustar el nivel de ritmo
            Slider(
                value = ritmo, // Valor actual del slider
                onValueChange = { nuevoValor ->
                    ritmo = nuevoValor // Actualiza el estado cuando se desliza
                },
                valueRange = 0f..10f, // Rango de valores posibles (0 a 10)
                steps = 9, // Número de pasos intermedios (10 valores posibles: 0,1,2,...,10)
                modifier = Modifier.width(350.dp) // Ancho fijo del slider
            )

            // Espacio vertical de separación
            Spacer(Modifier.height(16.dp))

            // Botón para comprobar si el usuario puede entrar
            Button(
                onClick = {
                    // Lógica para determinar el mensaje basado en las condiciones
                    mensaje = when {
                        !quieresEntrar -> "No quieres entrar" // Si no activó el switch
                        ritmo < 7 -> "No tienes suficiente ritmo, no pasas" // Si ritmo < 7
                        else -> "Tienes buen ritmo, adelante" // Si cumple todas las condiciones
                    }
                }
            ) {
                Text("Comprobar si puedes entrar") // CORRECCIÓN: "Comprobar" en lugar de "Comporbar"
            }

            // Espacio vertical de separación
            Spacer(Modifier.height(16.dp))

            // Muestra el mensaje solo si no es null
            if (mensaje != null) { // Si mensaje es distinto de null
                Text(
                    text = mensaje!!, // CORRECCIÓN: Mejor usar mensaje ?: "" y eliminar !!
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


