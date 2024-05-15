package com.galaxy.gifsearcher.giflist.data.repository

import androidx.paging.PagingData
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import com.galaxy.gifsearcher.giflist.domain.repository.GifRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository: GifRepository {

    private val gifs = listOf(
        Gif("0","gif0"),
        Gif("1","gif1"),
        Gif("2","gif2"),
        Gif("3","gif3")

    )
    override fun getGifs(query: String): Flow<PagingData<Gif>> {
        return flow { emit(PagingData.from(gifs)) }
    }
}