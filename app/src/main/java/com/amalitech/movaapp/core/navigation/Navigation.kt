package com.amalitech.movaapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amalitech.movaapp.ft_onboarding.presentation.login.LoginScreen
import com.amalitech.movaapp.ft_onboarding.presentation.login.LoginViewModel
import com.amalitech.movaapp.ft_onboarding.presentation.welcome.WelcomeScreen


@Composable
fun MovaNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.WELCOME
    ) {
        composable(Route.WELCOME) {
            WelcomeScreen{
                navController.navigate(Route.LOGIN)
            }
        }
        composable(Route.LOGIN) {
            LoginScreen(LoginViewModel())
        }
    }

}