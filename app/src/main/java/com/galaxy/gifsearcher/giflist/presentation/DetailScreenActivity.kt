package com.galaxy.gifsearcher.giflist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import com.galaxy.gifsearcher.giflist.presentation.components.ErrorScreen
import com.galaxy.gifsearcher.giflist.presentation.gif.GifViewModel
import com.galaxy.gifsearcher.giflist.presentation.gif.screen.GifScreen
import com.galaxy.gifsearcher.ui.theme.GifSearcherTheme

class DetailScreenActivity : ComponentActivity() {

    private val viewModel: GifViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GifSearcherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val id = intent.getStringExtra("id")
                    val url = intent.getStringExtra("url")
                    val width = intent.getFloatExtra("width",-1f)
                    val height = intent.getFloatExtra("height", -1f)
                    if(id != null && url != null && width != -1f && height != -1f){
                        viewModel.setGif(Gif(id,url,height,width))
                        GifScreen(viewModel)
                    }else{
                        ErrorScreen()
                    }
                }
            }
        }
    }
}

