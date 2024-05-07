package com.gkp.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gkp.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var isLoadingComplete = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Splash Screen must before setContent and enableEdgeToEdge
        installSplashScreen().setKeepOnScreenCondition {
            !isLoadingComplete
        }
        enableEdgeToEdge(
            SystemBarStyle.light(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb()
            )
        ) // System bar transparent
        setContent {
            var isDarkTheme by remember {
                mutableStateOf<Boolean?>(null)
            }

            NewsAppTheme(
                darkTheme = isDarkTheme ?: isSystemInDarkTheme()
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsApp(
                        onDarkTheme = {
                            isDarkTheme = it
                        },
                        onLoadingComplete = { isLoadingComplete = true }
                    )
                }
            }
        }
    }
}
