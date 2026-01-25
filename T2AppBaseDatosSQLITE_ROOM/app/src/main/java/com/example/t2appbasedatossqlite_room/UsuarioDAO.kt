package com.example.t2appbasedatossqlite_room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDAO {
    @Query("SELECT * FROM usuarios")
    fun obtenerTodosLosUsuarios(): List<Usuario>

    @Insert
    fun insertarUsuario(usuario: Usuario):Long

    @Update
    fun actualizarUsuario(usuario: Usuario)

    @Delete
    fun eliminarUsuario(usuario: Usuario)
}