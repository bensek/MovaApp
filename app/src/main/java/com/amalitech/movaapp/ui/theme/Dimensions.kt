package com.amalitech.movaapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val padding: Dp = 16.dp,
    val inputHeight: Dp = 40.dp,
    val inputCornerSize: Dp = 8.dp,
    val iconSize: Dp = 48.dp
)

val LocalDimensions = compositionLocalOf { Dimensions() }