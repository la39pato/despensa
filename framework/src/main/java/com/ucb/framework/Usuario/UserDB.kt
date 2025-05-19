package com.ucb.framework.Usuario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "usuarios")
data class UserDB(
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "correo")
    val correo: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "productos")
    val productos: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
