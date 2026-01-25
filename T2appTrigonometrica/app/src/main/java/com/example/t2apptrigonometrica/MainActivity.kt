package com.example.t2apptrigonometrica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text

import androidx.compose.ui.tooling.preview.Preview
import com.example.t2apptrigonometrica.ui.theme.T2appTrigonometricaTheme
import android.util.Log
import android.view.Surface
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    //Cambio en la surface (mejorar API)
                    .padding(top = 50.dp, start = 20.dp, end = 20.dp),


                onClick = { /* acción */ },
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 6.dp,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                val navegar = rememberNavController()
                NavHost(
                    navController = navegar,
                    startDestination = "inicio"
                ) {
                    composable("inicio") {
                        // PantallaInicioJugadoresLeganes(navegar)
                        MyApp()
                        // Modificamos la llamada enviando el NavHost
                    }
                    /* Comentamos para hacer el @Preview
                    composable("detalle/{jugador}") { backStack ->
                        val nombreJugador = backStack.arguments?.getString("jugador")
                        PantallaDetalleJugadorLeganes(nombreJugador, navegar)
                    }
                    */
                }

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MyApp() {
        var ladoA by rememberSaveable {
            mutableStateOf("")
        }
        var ladoB by rememberSaveable {
            mutableStateOf("")
        }

        var hypoC by rememberSaveable {
            mutableStateOf("")
        }
        var anguloA by rememberSaveable {
            mutableStateOf("")
        }
        var anguloB by rememberSaveable {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Cambiamos el padding porque ya lo hace Surface
                .padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),  // espacio entre textos
            horizontalAlignment = Alignment.CenterHorizontally  // centra los textos
        ) {

            /*Row(
                modifier = Modifier
                    .fillMaxWidth(),
                //  .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp), // separación entre textos
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text("Lado a   ")


                TextField(
                    value = ladoA,
                    onValueChange = { ladoA = it },
                    placeholder = { Text("0.0") },
                    //label = { Text("0.0") },
                    modifier = Modifier
                        .width(150.dp) //El textField se lo come
                    //.padding(16.dp)
                )


                Button(
                    onClick = {ladoA=""}
                    , //Poner a cero el LadoA
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp) //cambio
                ) {
                    Text(
                        "X"
                    )
                }
            }*/
            Fila(
                "Lado a   ",
                ladoA,
                onValueChangeState = {
                    ladoB = it
                },
                onValueChangeStateX = { ladoA = "" }

            )
            Fila(
                "Lado b   ",
                ladoB,
                onValueChangeState = {
                    ladoB = it
                },
                onValueChangeStateX = { ladoB = "" }

            )
            Fila(
                "Hypot c  ",
                hypoC,
                onValueChangeState = {
                    anguloA = it
                },
                onValueChangeStateX = { anguloA = "" }

            )
            Fila(
                "Angulo A",
                anguloA,
                onValueChangeState = {
                    anguloA = it
                },
                onValueChangeStateX = { anguloA = "" }

            )
            Fila(
                "Angulo B",
                anguloB,
                onValueChangeState = {
                    anguloB = it
                },
                onValueChangeStateX = { anguloB = "" }

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                //  .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp), // separación entre textos
                verticalAlignment = Alignment.CenterVertically

            ) {

                Button(
                    onClick = {
                        /*
                        Mirar que lado a y lado b no estén vacios .isEmpty
                        Formato en la salida del textField, 9 dígitos
                        Entrada de valores, solo teclado numérico
                        keyboardType: KeyboardType = KeyboardType.Number (Caja Texto)
                        Limpiar
                        Evento al pulsar intro en ladoA y ladoB
                        Calcular - mostrar calculos - limpiar
                         */
                        if (!(ladoA.isEmpty() || ladoB.isEmpty())) {
                            var lA = ladoA.toDouble()
                            var lB = ladoB.toDouble()
                            var hipo = Math.sqrt(Math.pow(lA, 2.0) + Math.pow(lB, 2.0))
                            Log.d("hipo", "hipo = ${hipo}")
                            hypoC = hipo.toString()
                        } else {
                            Log.d("LadoA y LadoB", "Están vacíos")
                        }
                    }, // Recogemos datos y pintamos
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Calcular"
                    )
                }
                Button(
                    onClick = {
                        ladoA = ""
                        ladoB = ""
                        hypoC = ""
                        anguloA = ""
                        anguloB = ""
                    }, // poner todos textfield a ""

                    modifier = Modifier
                        // .fillMaxWidth()
                        .padding(16.dp),

                    ) {
                    Text(
                        text = "Limpiar"
                    )
                }
            }
        }


    }

    @Composable
    fun Fila(
        textoLado: String,
        lado: String,                   // Valor actual del texto que hay en el campo
        onValueChangeState: (String) -> Unit, // Función que se ejecuta cuando el usuario escribe
        onValueChangeStateX: () -> Unit, // Función que se ejecuta cuando el usuario escribe
        //Mirar porque al poner onValueChange (String) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp), // separación entre textos
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(textoLado)

            TextField(
                value = lado,
                onValueChange = onValueChangeState,
                placeholder = { Text("0.0") },
                //label = { Text("0.0") },
                modifier = Modifier
                    .width(150.dp) //El textField se lo come
                //.padding(16.dp),
                ,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, // Teclado numérico, texto, email, etc.
                    imeAction = ImeAction.Next      // Botón del teclado: Next
                )
            )

            /*
            Button(
                onClick = onValueChangeStateX, //Poner a cero el LadoA
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Log.d("","")
                Text(
                    "X"
                )
            }
            */
            Card(
                modifier = Modifier
                    .fillMaxWidth() // cambio
                    .padding(1.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                onClick = onValueChangeStateX // Ponemos el onClick

            ) {
                Column(
                    //modifier =
                    Modifier.padding(10.dp)
                )
                //Text("X")
                {
                    Text(
                        text = "X",
                        modifier = Modifier
                            .fillMaxWidth(),         // cambio
                        // .padding(16.dp),         // deja espacio alrededor
                        textAlign = TextAlign.Center, // centra el texto horizontalmente
                        style = TextStyle(           // define estilo básico
                            fontSize = 20.sp,        // tamaño de letra
                            fontWeight = FontWeight.Bold, // negrita
                            color = Color.Black       // color del texto
                        )
                    )
                }
            }
        }
    }
}


