package com.amalitech.movaapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amalitech.movaapp.ft_onboarding.login.LoginScreen
import com.amalitech.movaapp.ft_onboarding.login.LoginViewModel
import com.amalitech.movaapp.ft_onboarding.welcome.WelcomeScreen


@Composable
fun BaseNavigation() {

    val navController = rememberNavController()
    val navBarController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.WelcomeScreen.route
    ) {
        composable(Screen.WelcomeScreen.route) {
            WelcomeScreen{
                navController.navigate(Screen.LoginScreen.route)
            }
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(LoginViewModel()) {
                navController.navigate(Screen.AppScaffold.route)
            }
        }

        composable(Screen.AppScaffold.route) {
            AppScaffold(navBarController) {
                navController.navigate(Screen.WelcomeScreen.route)
            }
        }
    }

}