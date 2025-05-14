package com.ucb.despensa.di

import android.content.Context
import androidx.room.Room
import com.ucb.data.repository.AuthRepository
import com.ucb.data.repository.ProductoRepository
import com.ucb.despensa.R
import com.ucb.framework.Productos.AppDatabase
import com.ucb.framework.Productos.ProductDao
import com.ucb.framework.Productos.ProductRepositoryImpl
import com.ucb.framework.Usuario.UserDao
import com.ucb.framework.Usuario.UserRepositoryImpl
import com.ucb.usecases.Producto.ActualizarProducto
import com.ucb.usecases.Producto.AgregarProducto
import com.ucb.usecases.Producto.EliminarProducto
import com.ucb.usecases.Producto.ObtenerProductos
import com.ucb.usecases.Usuario.IniciarSesion
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "productos_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    // Repositories
    @Provides
    @Singleton
    fun provideProductoRepository(dao: ProductDao): ProductoRepository {
        return ProductRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(dao: UserDao): AuthRepository {
        return UserRepositoryImpl(dao)
    }

    // Usecases: Productos
    @Provides
    @Singleton
    fun provideInsertarProducto(repo: ProductoRepository): AgregarProducto {
        return AgregarProducto(repo)
    }

    @Provides
    @Singleton
    fun provideObtenerProductos(repo: ProductoRepository): ObtenerProductos {
        return ObtenerProductos(repo)
    }

    @Provides
    @Singleton
    fun provideEliminarProducto(repo: ProductoRepository): EliminarProducto {
        return EliminarProducto(repo)
    }

    @Provides
    @Singleton
    fun provideActualizarProducto(repo: ProductoRepository): ActualizarProducto {
        return ActualizarProducto(repo)
    }

    // Usecases: Auth
    @Provides
    @Singleton
    fun provideIniciarSesion(repo: AuthRepository): IniciarSesion {
        return IniciarSesion(repo)
    }

}