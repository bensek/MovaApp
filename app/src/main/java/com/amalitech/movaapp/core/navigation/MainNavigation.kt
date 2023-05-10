package com.amalitech.movaapp.core.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = BottomNavScreens.Home.route) {
        composable(BottomNavScreens.Home.route) {
            HomeScreen()
        }
        composable(BottomNavScreens.Explore.route) {
            ExploreScreen()
        }
        composable(BottomNavScreens.MyList.route) {
            MyListScreen()
        }
        composable(BottomNavScreens.Profile.route) {
            ProfileScreen()
        }
    }

}

@Composable
fun HomeScreen() {
    Text(text = "Home")

}
@Composable
fun ExploreScreen() {
    Text(text = "Explore")
}

@Composable
fun MyListScreen() {
    Text(text = "My List")
}

@Composable
fun ProfileScreen() {
    Text(text = "Profile")
}