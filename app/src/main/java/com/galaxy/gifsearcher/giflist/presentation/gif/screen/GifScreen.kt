package com.galaxy.gifsearcher.giflist.presentation.gif.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.galaxy.gifsearcher.giflist.presentation.components.GifCard
import com.galaxy.gifsearcher.giflist.presentation.gif.GifViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GifScreen(
    viewModel: GifViewModel = hiltViewModel(),
    animatedContentScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope
){

    val gif = viewModel.gif.value
    var backHandlerEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(550)
        backHandlerEnabled = false
    }
    BackHandler(backHandlerEnabled){}

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