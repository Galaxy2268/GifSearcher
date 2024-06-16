package com.galaxy.gifsearcher.giflist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.galaxy.gifsearcher.giflist.presentation.gif.screen.GifScreen
import com.galaxy.gifsearcher.giflist.presentation.gifs.screen.GifsScreen
import com.galaxy.gifsearcher.giflist.presentation.util.Screen
import com.galaxy.gifsearcher.ui.theme.GifSearcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GifSearcherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SharedTransitionLayout {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.GifsScreen
                        ){
                            composable<Screen.GifsScreen>{
                                GifsScreen(
                                    navController = navController,
                                    animatedContentScope = this,
                                    sharedTransitionScope = this@SharedTransitionLayout,
                                )
                            }

                            composable<Screen.GifScreen>{
                                GifScreen(
                                    animatedContentScope = this,
                                    sharedTransitionScope = this@SharedTransitionLayout
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

