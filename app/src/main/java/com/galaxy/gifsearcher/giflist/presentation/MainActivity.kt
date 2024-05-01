package com.galaxy.gifsearcher.giflist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.galaxy.gifsearcher.giflist.presentation.gif.screen.GifScreen
import com.galaxy.gifsearcher.giflist.presentation.gifs.screen.GifsScreen
import com.galaxy.gifsearcher.giflist.presentation.util.Screen
import com.galaxy.gifsearcher.ui.theme.GifSearcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GifSearcherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.GifsScreen.route
                    ){
                        composable(route = Screen.GifsScreen.route){
                            GifsScreen(navController = navController)
                        }

                        composable(
                            route = Screen.GifScreen.route + "?id={id}&url={url}",
                            arguments = listOf(
                                navArgument(name = "id"){
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(name = "url"){
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ){
                            GifScreen()
                        }
                    }
                }
            }
        }
    }
}

