package com.amalitech.movaapp.core.navigation.bottom_appbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.amalitech.movaapp.core.navigation.BottomNavScreens
import com.amalitech.movaapp.ui.theme.LightGray
import com.amalitech.movaapp.ui.theme.RedMain

@Composable
fun BottomAppBar(
    navController: NavHostController
) {
    val currentRoute = currentRoute(navController = navController)
    val bottomNavScreens = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Explore,
        BottomNavScreens.MyList,
        BottomNavScreens.Profile,
    )

    BottomNavigation(
        modifier = Modifier
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                shadowElevation = 2.2f
            },
        contentColor = RedMain,
        backgroundColor = Color.White,
        elevation = 8.dp
    ) {
        bottomNavScreens.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.route ,
                selectedContentColor = RedMain,
                unselectedContentColor = LightGray,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label
                    )
                },
                label = {
                    Text(text = screen.label)
                },
                enabled = true
            )
        }
    }

}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route

}
