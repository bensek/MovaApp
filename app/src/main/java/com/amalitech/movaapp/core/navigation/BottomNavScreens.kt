package com.amalitech.movaapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreens(val route: String, val label:String, val icon: ImageVector) {
    object Home: BottomNavScreens("home", "Home", Icons.Filled.Home)
    object Explore: BottomNavScreens("explore", "Explore", Icons.Filled.Search)
    object MyList: BottomNavScreens("my_list", "My List", Icons.Filled.List)
    object Profile: BottomNavScreens("profile", "Profile", Icons.Filled.Person)
}