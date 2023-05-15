package com.amalitech.movaapp.ft_onboarding.register

data class RegisterUiState(
    val name: String = "",
    val emailAddress: String = "",
    val phone: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)
