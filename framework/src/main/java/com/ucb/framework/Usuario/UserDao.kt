package com.ucb.framework.Usuario

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM usuarios WHERE correo = :correo AND password = :password")
    suspend fun login(correo: String, password: String): UserEntity?

    @Query("SELECT * FROM usuarios WHERE correo = :correo")
    suspend fun getByEmail(correo: String): UserEntity?
}