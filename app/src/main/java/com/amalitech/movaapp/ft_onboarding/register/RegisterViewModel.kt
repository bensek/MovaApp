package com.amalitech.movaapp.ft_onboarding.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel() {

    private var _registerUiState: MutableStateFlow<RegisterUiState> = MutableStateFlow(
        RegisterUiState()
    )
    val registerUiState = _registerUiState.asStateFlow()

    fun onEmailChanged(text: String) {
        _registerUiState.update {
            it.copy(emailAddress = text)
        }
    }

    fun onPasswordChanged(text: String) {
        _registerUiState.update {
            it.copy(password = text)
        }
    }

    fun onSignIn() {

    }

    fun onNameChanged(it: String) {

    }

    fun onPhoneChanged(it: String) {

    }

    fun onConfirmPasswordChanged(it: String) {

    }
}