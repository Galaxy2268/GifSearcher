package com.galaxy.gifsearcher.giflist.presentation.gifs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.galaxy.gifsearcher.giflist.domain.repository.GifRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GifsViewModel @Inject constructor(
    repository: GifRepository
): ViewModel() {

    val gifPagingFlow = repository.getTrending().cachedIn(viewModelScope)

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


}