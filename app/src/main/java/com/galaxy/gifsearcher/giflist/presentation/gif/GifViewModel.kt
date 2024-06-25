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

    private val _gif = mutableStateOf(Gif("","", 0f,0f))
    val gif: State<Gif> = _gif

    init {
        val id = savedStateHandle.get<String>("id")
        val url = savedStateHandle.get<String>("url")
        val height = savedStateHandle.get<Float>("height")
        val width = savedStateHandle.get<Float>("width")

        if (id != null && url != null && height != null && width != null) {
            _gif.value = Gif(id, url, height, width)
        }
    }

}