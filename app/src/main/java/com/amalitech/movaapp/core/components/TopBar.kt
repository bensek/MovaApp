package com.amalitech.movaapp.core.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amalitech.movaapp.ui.theme.TextBlack

@Composable
fun ToolBar(
    title: String,
    isTransparent: Boolean = false,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {
                navigateUp()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "back", tint = if (isTransparent) Color.White else TextBlack)
            }
        },
        backgroundColor = if (isTransparent) Color.Transparent else Color.White,
        contentColor = TextBlack,
        elevation = 0.dp
    )
}