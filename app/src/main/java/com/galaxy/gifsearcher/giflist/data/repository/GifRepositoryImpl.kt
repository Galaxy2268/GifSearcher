package com.galaxy.gifsearcher.giflist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.galaxy.gifsearcher.giflist.data.remote.GiphyApi
import com.galaxy.gifsearcher.giflist.data.remote.GiphyPagingSource
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import com.galaxy.gifsearcher.giflist.domain.repository.GifRepository
import kotlinx.coroutines.flow.Flow

class GifRepositoryImpl(
    private val api: GiphyApi
): GifRepository {
    override fun getGifs(query: String): Flow<PagingData<Gif>> {
        return Pager(
            config = PagingConfig(pageSize = 25),
            pagingSourceFactory = { GiphyPagingSource(api, query) },
        ).flow
    }
}