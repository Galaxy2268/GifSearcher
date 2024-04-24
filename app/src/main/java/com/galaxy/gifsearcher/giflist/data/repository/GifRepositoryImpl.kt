package com.galaxy.gifsearcher.giflist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.galaxy.gifsearcher.giflist.data.remote.Response
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import com.galaxy.gifsearcher.giflist.domain.repository.GifRepository
import kotlinx.coroutines.flow.Flow

class GifRepositoryImpl(
    private val pager: Pager<Int, Gif>
): GifRepository {
    override fun getTrending(): Flow<PagingData<Gif>> {
        return pager.flow
    }
}