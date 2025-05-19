package com.ucb.framework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ucb.framework.Productos.ProductDB
import com.ucb.framework.Productos.ProductDao
import com.ucb.framework.Usuario.UserDao

@Database(entities = [ProductDB::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "mi_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
