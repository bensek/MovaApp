package com.amalitech.movaapp.ft_onboarding.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.movaapp.R
import com.amalitech.movaapp.core.components.ButtonPrimary
import com.amalitech.movaapp.ui.theme.EditTextBackground
import com.amalitech.movaapp.ui.theme.GrayStroke
import com.amalitech.movaapp.ui.theme.LocalDimensions

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    val state = viewModel.loginUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
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

        EmailTextField(value = state.value.emailAddress, onValueChanged = { viewModel.onEmailChanged(it) } )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(value = state.value.password, onValueChanged = { viewModel.onPasswordChanged(it) })

        Spacer(modifier = Modifier.height(32.dp))

        ButtonPrimary(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Sign in"
        ) {
            viewModel.onSignIn()
        }
    }
}

@Composable
fun EmailTextField(
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(EditTextBackground),
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        placeholder = {
            Text(text = "Email Address", color = GrayStroke)
        },
        shape = RoundedCornerShape(LocalDimensions.current.inputCornerSize),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = EditTextBackground)
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(EditTextBackground),
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        placeholder = {
            Text(text = "Password", color = GrayStroke)
        },
        shape = RoundedCornerShape(LocalDimensions.current.inputCornerSize),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = EditTextBackground)
    )

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen(LoginViewModel())
}