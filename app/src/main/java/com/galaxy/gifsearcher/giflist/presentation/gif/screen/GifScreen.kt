package com.galaxy.gifsearcher.giflist.presentation.gif.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.galaxy.gifsearcher.giflist.presentation.components.ErrorScreen
import com.galaxy.gifsearcher.giflist.presentation.components.GifCard
import com.galaxy.gifsearcher.giflist.presentation.gif.GifViewModel

@Composable
fun GifScreen(
    viewModel: GifViewModel = hiltViewModel(),
){

    val gif = viewModel.gif.value

    if (gif.url.isBlank()){
        ErrorScreen()
    }else{
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            GifCard(
                gif = gif,
                modifier = Modifier
                    .aspectRatio(gif.width / gif.height)
                    .padding(8.dp),
                contextMenu = true,
                onPress = {}
            )
        }
    }

}