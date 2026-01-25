package com.example.t2appbasedatossqlite_room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nombre: String,
    val correo: String
)