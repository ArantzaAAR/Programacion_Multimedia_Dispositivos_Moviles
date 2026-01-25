package com.example.t2apptrigonometrica

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.t2apptrigonometrica.ui.theme.t2apptrigonometricaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            t2apptrigonometricaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var ladoA by rememberSaveable { mutableStateOf("") }
    var ladoB by rememberSaveable { mutableStateOf("") }
    var hypoC by rememberSaveable { mutableStateOf("") }
    var anguloA by rememberSaveable { mutableStateOf("") }
    var anguloB by rememberSaveable { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Fila corregida para Lado A
            Fila(
                textoLado = "Lado a",
                lado = ladoA,
                onValueChangeState = { ladoA = it },
                onValueChangeStateX = { ladoA = "" }
            )

            // Fila corregida para Lado B
            Fila(
                textoLado = "Lado b",
                lado = ladoB,
                onValueChangeState = { ladoB = it },
                onValueChangeStateX = { ladoB = "" }
            )

            // Fila corregida para Hipotenusa
            Fila(
                textoLado = "Hypot c",
                lado = hypoC,
                onValueChangeState = { hypoC = it },
                onValueChangeStateX = { hypoC = "" }
            )

            // Fila corregida para Ángulo A
            Fila(
                textoLado = "Ángulo A",
                lado = anguloA,
                onValueChangeState = { anguloA = it },
                onValueChangeStateX = { anguloA = "" }
            )

            // Fila corregida para Ángulo B
            Fila(
                textoLado = "Ángulo B",
                lado = anguloB,
                onValueChangeState = { anguloB = it },
                onValueChangeStateX = { anguloB = "" }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        if (ladoA.isNotEmpty() && ladoB.isNotEmpty()) {
                            try {
                                val lA = ladoA.toDouble()
                                val lB = ladoB.toDouble()
                                val hipo = Math.sqrt(Math.pow(lA, 2.0) + Math.pow(lB, 2.0))
                                hypoC = String.format("%.2f", hipo)

                                // Calcular ángulos también
                                val anguloARad = Math.atan(lB / lA)
                                val anguloBRad = Math.PI / 2 - anguloARad
                                anguloA = String.format("%.2f", Math.toDegrees(anguloARad))
                                anguloB = String.format("%.2f", Math.toDegrees(anguloBRad))
                            } catch (e: NumberFormatException) {
                                Log.e("Error", "Número inválido: ${e.message}")
                            }
                        } else {
                            Log.d("Validación", "LadoA y LadoB deben tener valores")
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Calcular")
                }

                Button(
                    onClick = {
                        ladoA = ""
                        ladoB = ""
                        hypoC = ""
                        anguloA = ""
                        anguloB = ""
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Limpiar")
                }
            }
        }
    }
}

@Composable
fun Fila(
    textoLado: String,
    lado: String,
    onValueChangeState: (String) -> Unit,
    onValueChangeStateX: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textoLado,
            modifier = Modifier.width(80.dp)
        )

        TextField(
            value = lado,
            onValueChange = onValueChangeState,
            placeholder = { Text("0.0") },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        IconButton(
            onClick = onValueChangeStateX,
            modifier = Modifier.size(48.dp)
        ) {
            Text(
                text = "X",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    T2appTrigonometricaTheme {
        MyApp()
    }
}