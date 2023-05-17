package com.amalitech.movaapp.core.navigation

sealed class Screen(val route: String, val id: String = "") {
    object WelcomeScreen: Screen("welcome")
    object LoginScreen: Screen("login")
    object RegisterScreen: Screen("register")

    object AppScaffold: Screen("app_scaffold")

    object HomeScreen: Screen("home")
    object ExploreScreen: Screen("explore")
    object DetailScreen: Screen("detail")
    object GridScreen: Screen("grid_movies")

}