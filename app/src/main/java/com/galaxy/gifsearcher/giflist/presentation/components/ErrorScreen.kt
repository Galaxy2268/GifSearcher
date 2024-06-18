package com.galaxy.gifsearcher.giflist.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
){

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            text = "Something went wrong...",
            color = MaterialTheme.colorScheme.onErrorContainer,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}