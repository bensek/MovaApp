package com.amalitech.movaapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amalitech.movaapp.ft_home.grid.GridScreen
import com.amalitech.movaapp.ft_home.grid.GridViewModel
import com.amalitech.movaapp.ft_onboarding.login.LoginScreen
import com.amalitech.movaapp.ft_onboarding.login.LoginViewModel
import com.amalitech.movaapp.ft_onboarding.register.RegisterScreen
import com.amalitech.movaapp.ft_onboarding.register.RegisterViewModel
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
            LoginScreen(
                viewModel = LoginViewModel(),
                goToMainScreen = { navController.navigate(Screen.AppScaffold.route) },
                goToSignUpScreen = { navController.navigate(Screen.RegisterScreen.route) }
            )
        }

        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                viewModel = RegisterViewModel(),
                login = { navController.navigate(Screen.LoginScreen.route) }
            )
        }

        composable(Screen.AppScaffold.route) {
            AppScaffold(navController, navBarController) {
                navController.navigate(Screen.WelcomeScreen.route)
            }
        }

        composable(Screen.GridScreen.route + "/{type}") { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type")

            GridScreen(
                navController = navController,
                viewModel = GridViewModel(type!!),
                movieType = type
            )
        }

    }

}
