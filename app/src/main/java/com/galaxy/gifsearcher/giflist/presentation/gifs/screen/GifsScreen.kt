package com.galaxy.gifsearcher.giflist.presentation.gifs.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.galaxy.gifsearcher.giflist.presentation.components.GifCard
import com.galaxy.gifsearcher.giflist.presentation.gifs.GifsViewModel
import com.galaxy.gifsearcher.giflist.presentation.util.Screen


@Composable
fun GifsScreen(
    viewModel: GifsViewModel = hiltViewModel(),
    navController: NavController
){
    val gifs = viewModel.gifsPagingFlow.collectAsLazyPagingItems()
    val searchText = viewModel.searchText.collectAsState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

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
        CustomSearchField(
            searchText = searchText.value,
            onValueChange = viewModel::onSearchTextChange,
            onClear = viewModel::clearSearchText,
            focusRequester = focusRequester,
            focusManager = focusManager
        )
        Spacer(modifier = Modifier.height(16.dp))
        if(gifs.loadState.refresh is LoadState.Loading){
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ){
                items(count = gifs.itemCount){index ->
                    val gif = gifs[index]
                    gif?.let{
                        GifCard(
                            gif = it,
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable { navController.navigate("${Screen.GifScreen.route}?id=${it.id}&url=${it.url}")}
                        )
                    }
                }
            }
        }
    }

}

