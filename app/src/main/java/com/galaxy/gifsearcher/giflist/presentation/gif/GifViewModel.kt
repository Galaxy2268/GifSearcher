package com.galaxy.gifsearcher.giflist.presentation.gif

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _gif = mutableStateOf(Gif("",""))
    val gif: State<Gif> = _gif

    init {
        val id = savedStateHandle.get<String>("id")
        val url = savedStateHandle.get<String>("url")

        if(id != null && url != null){
            _gif.value = gif.value.copy(id = id, url = url)
        }
    }

}