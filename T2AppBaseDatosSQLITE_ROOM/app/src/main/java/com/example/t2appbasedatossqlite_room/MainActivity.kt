package com.example.t2appbasedatossqlite_room

import android.content.ContentValues
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.t2appbasedatossqlite_room.ui.theme.T2AppBaseDatosSQLITE_ROOMTheme

class MainActivity : ComponentActivity() {

    private lateinit var usuarioDAO: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        // Inicializa el ayudante de base de datos
        var dataBase = AppDatabase.getDatabase(this)
        usuarioDAO = dataBase.usuarioDAO()

        setContent {
            T2AppBaseDatosSQLITE_ROOMTheme {
                MyApp(ayudante = usuarioDAO)
            }
        }
    }
    @Composable
    fun MyApp(ayudante: UsuarioDAO){

        // Lista de usuarios
        val usuarios = remember {
            mutableStateListOf<Usuario>()
        }

        // Cargar los usuarios desde la base de datos al iniciar
        LaunchedEffect(Unit) {
            usuarios.addAll(ayudante.obtenerTodosLosUsuarios())
        }

        // Estado para el modo edición
        var enModoEdicion by remember {
            mutableStateOf(false)
        }
        var usuarioSeleccionado by remember {
            mutableStateOf(Usuario(0, "", ""))
        }

        // Diseño principal
        Column(modifier = Modifier.fillMaxSize()) {
            // Pantalla para agregar o editar usuarios
            PantallaAgregarUsuario(
                usuario = usuarioSeleccionado,
                enModoEdicion = enModoEdicion,
                onUsuarioCambio = { usuarioSeleccionado = it },
                onGuardar = {
                    if (enModoEdicion) {
                        // Actualizar usuario existente
                        ayudante.actualizarUsuario(usuarioSeleccionado)
                        val index = usuarios.indexOfFirst { it.id == usuarioSeleccionado.id }
                        if (index != -1) {
                            usuarios[index] = usuarioSeleccionado
                        }
                    } else {
                        // Agregar nuevo usuario
                        val id = ayudante.insertarUsuario(usuarioSeleccionado).toInt()
                        usuarios.add(usuarioSeleccionado.copy(id = id))
                    }
                    // Reiniciar el formulario
                    enModoEdicion = false
                    usuarioSeleccionado = Usuario(0, "", "")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Pantalla para listar usuarios
            PantallaListaUsuarios(
                usuarios = usuarios,
                onActualizar = { usuario ->
                    // Preparar para editar
                    enModoEdicion = true
                    usuarioSeleccionado = usuario
                },
                onEliminar = { usuario ->
                    // Eliminar usuario
                    ayudante.eliminarUsuario(usuario)
                    usuarios.remove(usuario)
                }
            )
        }
    }
}

@Composable
fun PantallaListaUsuarios(
    usuarios: List<Usuario>,
    onActualizar: (Usuario) -> Unit,
    onEliminar: (Usuario) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(usuarios) { usuario ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "ID: ${usuario.id}")
                    Text(text = "Nombre: ${usuario.nombre}")
                    Text(text = "Correo: ${usuario.correo}")
                }
                Row {
                    Button(onClick = { onActualizar(usuario) }) {
                        Text("Editar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { onEliminar(usuario) }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}

@Composable
fun PantallaAgregarUsuario(
    usuario: Usuario,
    enModoEdicion: Boolean,
    onUsuarioCambio: (Usuario) -> Unit,
    onGuardar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = usuario.nombre,
            onValueChange = { onUsuarioCambio(usuario.copy(nombre = it)) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = usuario.correo,
            onValueChange = { onUsuarioCambio(usuario.copy(correo = it)) },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onGuardar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (enModoEdicion) "Actualizar Usuario" else "Guardar Usuario")
        }
    }

}