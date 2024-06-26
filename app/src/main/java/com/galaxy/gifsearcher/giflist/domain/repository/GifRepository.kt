package com.galaxy.gifsearcher.giflist.domain.repository

import androidx.paging.PagingData
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import kotlinx.coroutines.flow.Flow

interface GifRepository {
     fun getGifs(query: String): Flow<PagingData<Gif>>
}