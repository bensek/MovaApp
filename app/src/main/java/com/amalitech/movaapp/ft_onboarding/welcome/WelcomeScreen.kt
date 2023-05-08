package com.amalitech.movaapp.ft_onboarding.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.movaapp.R
import com.amalitech.movaapp.core.components.ButtonPrimary
import com.amalitech.movaapp.core.components.SocialButton
import com.amalitech.movaapp.ui.theme.GrayStroke
import com.amalitech.movaapp.ui.theme.LocalDimensions

@Composable
fun WelcomeScreen(
    goToLoginScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.welcome_image),
            contentDescription = stringResource(R.string.welcome_image_description),
            modifier = Modifier.height(120.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Let's you in",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        SocialButton(
            buttonText = "Continue with Facebook",
            buttonIcon = R.drawable.facebook
        ) {

        }

        Spacer(modifier = Modifier.height(16.dp))

        SocialButton(
            buttonText = "Continue with Google",
            buttonIcon = R.drawable.google
        ) {

        }

        Spacer(modifier = Modifier.height(16.dp))

        SocialButton(
            buttonText = "Continue with Apple",
            buttonIcon = R.drawable.apple
        ) {

        }

        Spacer(modifier = Modifier.height(48.dp))


        ButtonPrimary(
            buttonText = "Sign in with password",
            modifier = Modifier.fillMaxWidth()
        ) {
            goToLoginScreen()
        }

    }
}