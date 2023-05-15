package com.amalitech.movaapp.core.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.amalitech.movaapp.core.navigation.bottom_appbar.BottomAppBar

@Composable
fun AppScaffold(
    navController: NavHostController,
    navBarController: NavHostController,
    logout: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = {
            BottomAppBar(navController = navBarController)
        },
        scaffoldState = scaffoldState
    ) {
        println(it)
        MainNavigation(navController = navController, navBarController = navBarController)
    }

}