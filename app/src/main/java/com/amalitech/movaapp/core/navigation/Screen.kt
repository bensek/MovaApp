package com.amalitech.movaapp.core.navigation

sealed class Screen(val route: String) {
    object WelcomeScreen: Screen("welcome")
    object LoginScreen: Screen("login")

    object AppScaffold: Screen("app_scaffold")

    object HomeScreen: Screen("home")
    object ExploreScreen: Screen("explore")
}