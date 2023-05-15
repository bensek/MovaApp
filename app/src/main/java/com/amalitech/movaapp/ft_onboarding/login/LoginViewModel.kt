package com.amalitech.movaapp.ft_onboarding.login

import androidx.lifecycle.ViewModel
import com.amalitech.movaapp.ft_onboarding.register.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {

    private var _loginUiState: MutableStateFlow<RegisterUiState> = MutableStateFlow(RegisterUiState())
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