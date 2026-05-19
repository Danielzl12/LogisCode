package com.app.logiscode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.logiscode.model.LoginFormState
import com.app.logiscode.model.LoginState
import com.app.logiscode.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState.asStateFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun onEmailChange(email: String) {
        _formState.value = _formState.value.copy(email = email, emailError = null)
    }

    fun onPasswordChange(password: String) {
        _formState.value = _formState.value.copy(password = password, passwordError = null)
    }

    fun login() {
        val form = _formState.value
        var hasError = false

        if (!form.email.contains("@")) {
            _formState.value = _formState.value.copy(emailError = "Correo inválido")
            hasError = true
        }
        if (form.password.length < 6) {
            _formState.value = _formState.value.copy(passwordError = "Mínimo 6 caracteres")
            hasError = true
        }
        if (hasError) return

        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            authRepository.login(form.email, form.password)
                .onSuccess { userName ->
                    _loginState.value = LoginState.Success(userName)
                }
                .onFailure { error ->
                    _loginState.value = LoginState.Error(
                        error.message ?: "Error desconocido"
                    )
                }
        }
    }
}
