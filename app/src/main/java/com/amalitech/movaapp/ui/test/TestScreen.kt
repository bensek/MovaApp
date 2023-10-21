package com.amalitech.movaapp.ui.test

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TestScreen(
    viewModel: TestViewModel = hiltViewModel()
) {

    Text(text = viewModel.text)


}