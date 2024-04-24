package com.galaxy.gifsearcher.giflist.presentation.gifs.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.galaxy.gifsearcher.giflist.presentation.gifs.GifsViewModel


@Composable
fun GifsScreen(
    viewModel: GifsViewModel = hiltViewModel()
){
    val gifs = viewModel.gifPagingFlow.collectAsLazyPagingItems()
    val context = LocalContext.current


    LaunchedEffect(key1 = gifs.loadState) {
        if(gifs.loadState.refresh is LoadState.Error){
            Toast.makeText(
                context,
                "Error: " + (gifs.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        if(gifs.loadState.refresh is LoadState.Loading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }else{
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(count = gifs.itemCount){index ->
                    val gif = gifs[index]
                    AsyncImage(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp)),
                        model = gif?.url,
                        contentDescription = "Gif number $index",
                        )
                }

                item {
                    if(gifs.loadState.append is LoadState.Loading){
                        CircularProgressIndicator()
                    }
                }

            }
        }
    }

}