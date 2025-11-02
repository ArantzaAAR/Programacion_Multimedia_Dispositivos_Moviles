package com.example.apppropina002

import android.R.attr.enabled
import android.R.attr.fontWeight
import android.R.attr.label
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat
import com.example.appcuenta001.componentes.CajaTexto
import com.example.apppropina002.componentes.CajaTexto
import com.example.apppropina002.ui.theme.AppPropina002Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPropina002Theme {
                MyApp {
                    ContenidoPrincipal()
                }
            }
        }
    }
}

@Preview
@Composable
fun ContenidoPrincipal() {
    var totalCuenta by rememberSaveable {
        mutableStateOf("")
    }
    var estadoValido by rememberSaveable(totalCuenta) {
        mutableStateOf(totalCuenta.trim().isNotEmpty())
    }
    //controlamos el teclado
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 2.dp, color = Color.LightGray)

    ) {
        Column() {
            CajaTexto(
                valueState = totalCuenta,
                onValueChangeState = { totalCuenta = it.trim() },
                labelId = "Introduce la cuenta",
                enabled = true,
                isSingleLine = true,
                onAction = keyboardActions {
                    if (estadoValido) {
                        keyboardController?.hide()
                    }
                })
        }
    }
}

@Composable
fun TopCabecera(totalPorPersona: Double = 0.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()//que vaya hasta el final
            .height(150.dp)
            .clip(shape = CircleShape.copy(CornerSize(12.dp)))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            val total = "%.2f".format(totalPorPersona)
            Text(
                "Cantidad por persona",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                "$total€",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
//content indica que hay un composable Unit, para que se pueda propagar
fun MyApp(content: @Composable () -> Unit) {//con Unit el código se define arriba y la construccion se hace hacia abajo
    AppPropina002Theme {
        Surface {
            content()
        }
    }
}
