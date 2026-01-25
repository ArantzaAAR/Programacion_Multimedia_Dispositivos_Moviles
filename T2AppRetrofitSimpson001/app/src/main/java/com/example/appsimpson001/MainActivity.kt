package com.example.appsimpson001

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Surface

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appsimpson001.presentation.viewmodel.SimpsonsViewModel
import com.example.appsimpson001.screens.CharactersScreen
import com.example.appsimpson001.ui.theme.AppSimpson001Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppSimpson001Theme {
                Surface {
                    val vm: SimpsonsViewModel = viewModel()
                    CharactersScreen(vm)
                }
            }
        }
    }
}
