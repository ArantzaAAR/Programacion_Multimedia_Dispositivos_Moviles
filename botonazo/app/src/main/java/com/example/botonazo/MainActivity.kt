package com.example.botonazo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //PARA PONER EN MARCHA EL BOTON Y QUE ESCRIBA LAS VECES QUE SE PULSA
        val textViewContador : TextView = findViewById(R.id.textViewContador);
        val buttonPulsar : Button = findViewById(R.id.buttonPulsar);
        var pulsaciones =0;

        buttonPulsar.setOnClickListener {
            pulsaciones = pulsaciones + 1
            textViewContador.setText("Adios + ${pulsaciones}")
        }

        /* PIDE EL PESO Y LA ALTURA Y CON EL BOTON SE REALIZA EL CÁLCULO DE TU MASA CORPORAL
        val editTextNumberPeso : EditText = findViewById(R.id.editTextNumberPeso)
        val editTextTextAltura : EditText = findViewById(R.id.editTextTextAltura)
        val buttonImc : Button = findViewById(R.id.buttonImc)
        val textViewCalculo : TextView = findViewById(R.id.textViewCalculo)

        METODO QUE LO PONE A LA ESCUCHA
        buttonImc.SetOnClickListener {
            val peso : Double = editTextNumberPeso.text.toString().toDouble()
            val altura = editTextTextAltura.toString().toDouble()
            val calculo = peso / (pow(altura, 2.0))
            textViewCalculo.setText(calculo.toString())
         */
    }
}