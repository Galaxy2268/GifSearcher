package com.galaxy.gifsearcher.giflist.domain.usecases

import androidx.paging.PagingData
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import com.galaxy.gifsearcher.giflist.domain.repository.GifRepository
import kotlinx.coroutines.flow.Flow

class GetGifs(
    private val repository: GifRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Gif>> {
        return repository.getGifs(query)
    }

}