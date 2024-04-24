package com.galaxy.gifsearcher.giflist.domain.repository

import androidx.paging.PagingData
import com.galaxy.gifsearcher.giflist.data.remote.Response
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import kotlinx.coroutines.flow.Flow

interface GifRepository {
     fun getTrending(): Flow<PagingData<Gif>>
}