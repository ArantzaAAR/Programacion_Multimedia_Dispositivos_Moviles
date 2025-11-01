package com.example.a01_tutorial_moure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a01_tutorial_moure.ui.theme._01_Tutorial_MoureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {//Lo que se menciona dentro debe de ser @Compsable
            MyComponent()

        }
    }
}

@Composable
fun MyComponent(){
    Row(){
        MyImage()
        MyTexts()
    }
}
@Composable
fun MyImage(){//para meter imágenes
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Imagen de prueba") //Buenas practicas: poner el nombre de la imagen
}

@Composable
fun MyTexts(){
    Column() {//para Hacer columnas
        Text("¡Hola, Juan!")
        MyText("¿Preparado para empezar?")
    }
}

@Composable
fun MyText(text:String){//para meter texto
    Text(text)
}

@Preview//para previsualizar
@Composable
fun PreviewComponent(){
    MyComponent()
}

