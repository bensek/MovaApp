package com.amalitech.movaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.amalitech.movaapp.core.navigation.BaseNavigation
import com.amalitech.movaapp.ui.theme.MovaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovaAppTheme {
               BaseNavigation()
            }
        }
    }
}