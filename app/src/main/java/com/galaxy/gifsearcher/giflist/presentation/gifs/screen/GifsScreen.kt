package com.galaxy.gifsearcher.giflist.presentation.gifs.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.galaxy.gifsearcher.giflist.presentation.components.GifCard
import com.galaxy.gifsearcher.giflist.presentation.gifs.GifsViewModel
import com.galaxy.gifsearcher.giflist.presentation.util.Screen


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GifsScreen(
    viewModel: GifsViewModel = hiltViewModel(),
    navController: NavController,
    animatedContentScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope
){
    val gifs = viewModel.gifsPagingFlow.collectAsLazyPagingItems()
    val searchText = viewModel.searchText.collectAsState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val clipboardManager = LocalClipboardManager.current
    Log.d("testt", "${gifs.loadState}")

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
            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2)
            ){
                items(count = gifs.itemCount){index ->
                    val gif = gifs[index]
                    gif?.let{
                        GifCard(
                            gif = gif,
                            modifier = Modifier
                                .padding(4.dp)
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
                                        onTap = {
                                            navController.navigate("${Screen.GifScreen.route}?id=${gif.id}&url=${gif.url}")
                                        },
                                        onPress = {
                                            focusManager.clearFocus()
                                        }
                                    )
                                },
                            animatedContentScope = animatedContentScope,
                            sharedTransitionScope = sharedTransitionScope
                        )
                    }
                }
            }

        }
    }

}

