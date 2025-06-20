package com.ucb.framework.Usuario


import com.google.firebase.auth.FirebaseAuth
import com.ucb.data.repository.IAuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IAuthRepository {

    override suspend fun login(email: String, password: String): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun register(email: String, password: String): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}
