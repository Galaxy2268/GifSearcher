package com.galaxy.gifsearcher.giflist.presentation.gif.screen

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.galaxy.gifsearcher.giflist.presentation.components.GifCard
import com.galaxy.gifsearcher.giflist.presentation.gif.GifViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GifScreen(
    viewModel: GifViewModel = hiltViewModel(),
    animatedContentScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope
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
            animatedContentScope = animatedContentScope,
            sharedTransitionScope = sharedTransitionScope,
            contextMenu = true

        )
    }
}