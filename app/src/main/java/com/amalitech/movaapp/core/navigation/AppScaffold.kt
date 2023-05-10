package com.amalitech.movaapp.core.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.amalitech.movaapp.core.navigation.bottom_appbar.BottomAppBar

@Composable
fun AppScaffold(
    navController: NavHostController,
    logout: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = {
            BottomAppBar(navController = navController)
        },
        scaffoldState = scaffoldState
    ) {
        println(it)
        MainNavigation(navController = navController)
    }

}