package com.galaxy.gifsearcher.giflist.presentation.gif.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
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

    val clipboardManager = LocalClipboardManager.current
    val gif = viewModel.gif.value
    val context = LocalContext.current


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        GifCard(
            gif = gif,
            modifier = Modifier
                .padding(8.dp)
                .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        clipboardManager.setText(AnnotatedString(gif.url))
                        Toast.makeText(
                            context,
                            "Copied",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                )
            },
            animatedContentScope = animatedContentScope,
            sharedTransitionScope = sharedTransitionScope

        )
    }
}