package com.pratiksha.rojgarihub.presentation.auth.login

data class LoginState(
    val email: String = "cow@gmail.com",
    val password: String = "123456",
    val isPasswordVisible: Boolean = false,
    val rememberMe: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false
)
