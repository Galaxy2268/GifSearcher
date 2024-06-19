package com.galaxy.gifsearcher.giflist.presentation.gif.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.galaxy.gifsearcher.giflist.presentation.components.GifCard
import com.galaxy.gifsearcher.giflist.presentation.gif.GifViewModel


@Composable
fun GifScreen(
    viewModel: GifViewModel,
){

    val gif = viewModel.gif.value

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        GifCard(
            gif = gif,
            modifier = Modifier
                .padding(8.dp),
            contextMenu = true,
            onPress = {}

        )
    }
}