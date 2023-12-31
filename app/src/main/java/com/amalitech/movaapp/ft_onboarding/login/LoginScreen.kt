package com.amalitech.movaapp.ft_onboarding.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.movaapp.R
import com.amalitech.movaapp.core.components.ButtonPrimary
import com.amalitech.movaapp.core.components.InputField
import com.amalitech.movaapp.ft_onboarding.register.RegisterViewModel
import com.amalitech.movaapp.ui.theme.LocalDimensions
import com.amalitech.movaapp.ui.theme.RedMain

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    goToSignUpScreen: (Int) -> Unit,
    goToMainScreen: () -> Unit
) {
    val state = viewModel.loginUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(LocalDimensions.current.padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.welcome_image_description),
            modifier = Modifier.height(80.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Login to Your Account",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(label = "Email Address",
            leadingIcon = Icons.Default.Email,
            value = state.value.emailAddress, onValueChanged = { viewModel.onEmailChanged(it) } )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(label = "Password",
            leadingIcon = Icons.Default.Lock,
            value = state.value.password, onValueChanged = { viewModel.onPasswordChanged(it) })

        Spacer(modifier = Modifier.height(32.dp))

        ButtonPrimary(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Sign in"
        ) {
            goToMainScreen()
        }

        ClickableText(
            modifier = Modifier.padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle()) {
                    append("Don't have an account? ")
                }
                withStyle(style = SpanStyle(
                    color = RedMain
                )) {
                    append("Sign Up")
                }
            },
            onClick = goToSignUpScreen
        )
    }
}
