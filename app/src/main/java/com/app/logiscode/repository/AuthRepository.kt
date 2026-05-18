package com.app.logiscode.repository

import kotlinx.coroutines.delay

class AuthRepository {

    suspend fun login(email: String, password: String): Result<String> {
        delay(1500L)
        return if (email.contains("@") && password.length >= 6) {
            Result.success("Daniel")
        } else {
            Result.failure(Exception("Credenciales inválidas"))
        }
    }
}
