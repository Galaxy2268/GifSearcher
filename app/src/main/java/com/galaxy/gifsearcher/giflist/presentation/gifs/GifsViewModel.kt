package com.galaxy.gifsearcher.giflist.presentation.gifs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import com.galaxy.gifsearcher.giflist.domain.usecases.GifUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifsViewModel @Inject constructor(
    private val useCases: GifUseCases
): ViewModel() {


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _gifsPagingFlow = MutableStateFlow<PagingData<Gif>>(PagingData.empty())
    val gifsPagingFlow: Flow<PagingData<Gif>> = _gifsPagingFlow

    private var searchJob: Job? = null


    init {
        viewModelScope.launch {
            useCases.getGifs("").cachedIn(viewModelScope).collect{
                _gifsPagingFlow.value = it
            }
        }
    }



    fun onSearchTextChange(text: String){
        _searchText.value = text
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            useCases.getGifs(text).cachedIn(viewModelScope).collect{
                _gifsPagingFlow.value = it
            }
        }
    }

    fun clearSearchText(){
        if(_searchText.value.isBlank()) return

        _searchText.value = ""
        searchJob?.cancel()
        viewModelScope.launch {
            useCases.getGifs("").cachedIn(viewModelScope).collect{
                _gifsPagingFlow.value = it
            }
        }
    }



}