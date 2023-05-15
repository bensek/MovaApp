package com.amalitech.movaapp.ft_onboarding.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
fun RegisterScreen(
    viewModel: RegisterViewModel,
    login: (Int) -> Unit
) {
    val state = viewModel.registerUiState.collectAsState()

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
            contentDescription = null,
            modifier = Modifier.height(80.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Create Your Account",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(
            label = "Full Name",
            leadingIcon = Icons.Default.Person,
            value = state.value.name,
            onValueChanged = { viewModel.onNameChanged(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            label = "Email Address",
            leadingIcon = Icons.Default.Email,
            value = state.value.emailAddress,
            onValueChanged = { viewModel.onEmailChanged(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            label = "Phone Number",
            leadingIcon = Icons.Default.Call,
            value = state.value.phone,
            onValueChanged = { viewModel.onPhoneChanged(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            label = "Password",
            leadingIcon = Icons.Default.Lock,
            value = state.value.password,
            onValueChanged = { viewModel.onPasswordChanged(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            label = "Confirm Password",
            leadingIcon = Icons.Default.Lock,
            value = state.value.confirmPassword,
            onValueChanged = { viewModel.onConfirmPasswordChanged(it) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        ButtonPrimary(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Sign Up"
        ) {

        }

        ClickableText(
            modifier = Modifier.padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle()) {
                    append("Already have an account? ")
                }
                withStyle(style = SpanStyle(
                    color = RedMain
                )) {
                    append("Sign In")
                }
            },
            onClick = login
        )
    }
}
