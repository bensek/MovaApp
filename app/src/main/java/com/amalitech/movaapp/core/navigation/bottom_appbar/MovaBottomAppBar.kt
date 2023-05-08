package com.amalitech.movaapp.core.navigation.bottom_appbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

fun getBottomMenuItemsList(): List<BottomMenuItem> {
    val list = mutableListOf<BottomMenuItem>()

    list.add(BottomMenuItem(title = "Home", icon = Icons.Filled.Home))
    list.add(BottomMenuItem(title = "Explore", icon = Icons.Filled.Search))
    list.add(BottomMenuItem(title = "My List", icon = Icons.Filled.List))
    list.add(BottomMenuItem(title = "Profile", icon = Icons.Filled.Person))

    return list
}

@Composable
fun BottomAppBar(
    modifier: Modifier,
) {
    val bottomMenuItems = getBottomMenuItemsList()
    var selectedItem by remember {
        mutableStateOf("Home")
    }

    BottomNavigation(
        modifier = modifier,
    ) {
        bottomMenuItems.forEach { menuItem ->
            BottomNavigationItem(
                selected = selectedItem == menuItem.title ,
                onClick = {
                    selectedItem = menuItem.title
                },
                icon = {
                    Icon(
                        imageVector = menuItem.icon,
                        contentDescription = menuItem.title
                    )
                },
                label = {
                    Text(text = menuItem.title)
                },
                enabled = true
            )
        }

    }

}
