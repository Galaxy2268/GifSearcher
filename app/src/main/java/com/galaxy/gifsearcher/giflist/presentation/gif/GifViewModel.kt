package com.galaxy.gifsearcher.giflist.presentation.gif

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.galaxy.gifsearcher.giflist.domain.model.Gif


class GifViewModel: ViewModel() {

    private val _gif = mutableStateOf(Gif("","", 0f,0f))
    val gif: State<Gif> = _gif

    fun setGif(gif: Gif){
        _gif.value = gif
    }
}