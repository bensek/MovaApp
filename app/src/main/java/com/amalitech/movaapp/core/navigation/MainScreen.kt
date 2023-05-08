package com.amalitech.movaapp.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.amalitech.movaapp.core.navigation.bottom_appbar.BottomAppBar

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    clip = true
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    shadowElevation = 2.2f
                },

        )
    }


}