package com.galaxy.gifsearcher.giflist.presentation.util

import kotlinx.serialization.Serializable

sealed class Screen{
    @Serializable
    data object GifsScreen: Screen()
    @Serializable
    data class GifScreen(
        val id: String,
        val url: String
    ): Screen()
}