package com.galaxy.gifsearcher.giflist.presentation.gifs.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.galaxy.gifsearcher.giflist.presentation.gifs.GifsViewModel


@Composable
fun GifsScreen(
    viewModel: GifsViewModel = hiltViewModel()
){
    val gifs = viewModel.gifsPagingFlow.collectAsLazyPagingItems()
    val searchText = viewModel.searchText.collectAsState()
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        OutlinedTextField(
            value = searchText.value,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search it!")},
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        if(gifs.loadState.refresh is LoadState.Loading){
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }else{
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(count = gifs.itemCount){index ->
                    val gif = gifs[index]
                    gif?.let{ GifCard(gif = it, modifier = Modifier.padding(4.dp)) }
                }
            }
        }
    }

}