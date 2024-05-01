package com.galaxy.gifsearcher.giflist.presentation.util

sealed class Screen(val route: String){
    data object GifsScreen: Screen("gifs_screen")
    data object GifScreen: Screen("gif_screen")
}