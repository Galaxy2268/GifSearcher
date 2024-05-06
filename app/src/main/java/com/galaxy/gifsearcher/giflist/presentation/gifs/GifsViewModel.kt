package com.galaxy.gifsearcher.giflist.presentation.gifs

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import com.galaxy.gifsearcher.giflist.domain.usecases.GifUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class GifsViewModel @Inject constructor(
    private val useCases: GifUseCases
): ViewModel() {


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val gifsPagingFlow: Flow<PagingData<Gif>> = searchText
        .debounce(1000)
        .flatMapLatest {
            useCases.getGifs(it)
                .cachedIn(viewModelScope)
    }


    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

    fun clearSearchText(){
        _searchText.value = ""
    }



}