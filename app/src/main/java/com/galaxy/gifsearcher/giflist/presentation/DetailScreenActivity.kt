package com.galaxy.gifsearcher.giflist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.galaxy.gifsearcher.giflist.presentation.gif.screen.GifScreen
import com.galaxy.gifsearcher.giflist.presentation.ui.theme.GifSearcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GifSearcherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    GifScreen()
                }
            }
        }
    }
}

