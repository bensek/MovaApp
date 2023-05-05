package com.amalitech.movaapp.ft_onboarding.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {

    private var _loginUiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun onEmailChanged(text: String) {
        _loginUiState.update {
            it.copy(emailAddress = text)
        }
    }

    fun onPasswordChanged(text: String) {
        _loginUiState.update {
            it.copy(password = text)
        }
    }

    fun onSignIn() {

    }
}