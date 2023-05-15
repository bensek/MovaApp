package com.amalitech.movaapp.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.amalitech.movaapp.ft_home.home.HomeScreen
import com.amalitech.movaapp.ft_home.home.HomeViewModel


@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavScreens.Home.route,
        modifier = Modifier.padding(bottom = 44.dp)
    ) {
        composable(BottomNavScreens.Home.route) {
            HomeScreen(HomeViewModel())
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