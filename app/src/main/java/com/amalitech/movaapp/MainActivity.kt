package com.amalitech.movaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.amalitech.movaapp.core.navigation.MovaNavHost
import com.amalitech.movaapp.ft_onboarding.login.LoginScreen
import com.amalitech.movaapp.ft_onboarding.login.LoginViewModel
import com.amalitech.movaapp.ft_onboarding.welcome.WelcomeScreen
import com.amalitech.movaapp.ui.theme.MovaAppTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    MovaNavHost(navController = navController)
                }
            }
        }
    }
}
