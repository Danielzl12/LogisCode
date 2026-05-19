package com.app.logiscode.model

sealed interface LoginState {
    data object Idle : LoginState
    data object Loading : LoginState
    data class Success(val userName: String) : LoginState
    data class Error(val message: String) : LoginState
}

data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null
)
