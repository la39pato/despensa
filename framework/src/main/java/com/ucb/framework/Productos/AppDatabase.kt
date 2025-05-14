package com.ucb.framework.Productos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucb.framework.Usuario.UserDao

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
}