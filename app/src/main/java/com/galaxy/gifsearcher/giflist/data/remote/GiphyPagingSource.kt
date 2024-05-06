package com.galaxy.gifsearcher.giflist.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.galaxy.gifsearcher.giflist.data.mappers.toGif
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import retrofit2.HttpException
import java.io.IOException


class GiphyPagingSource(
    private val api: GiphyApi,
    private val query: String
):PagingSource<Int, Gif>() {

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(state.config.pageSize) ?: page.nextKey?.minus(state.config.pageSize)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize.coerceAtMost(50)

            val gifs = if(query.isBlank()) api.getTrending(
                offset = offset,
                limit = limit
            ).data.map { it.toGif() }
            else api.getWithSearch(
                offset = offset,
                limit = limit,
                query = query
            ).data.map { it.toGif() }

            val nextKey = if (gifs.size < limit) null else offset + limit
            val prevKey = if(offset == 0) null else offset - limit
            LoadResult.Page(gifs, prevKey, nextKey)
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }

    }

}



