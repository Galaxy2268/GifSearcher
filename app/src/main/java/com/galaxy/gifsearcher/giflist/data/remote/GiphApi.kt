package com.galaxy.gifsearcher.giflist.data.remote

import com.galaxy.gifsearcher.giflist.data.remote.util.GiphyKey.GIPHY_KEY
import com.galaxy.gifsearcher.giflist.domain.model.Giph
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphApi {
    @GET("trending?api_key=$GIPHY_KEY")
    suspend fun getTrending(
        @Query("offset") offset: Int
    ): List<Giph>

    @GET("search?api_key=$GIPHY_KEY")
    suspend fun getWithSearch(
        @Query("q") query: String,
        @Query("offset") offset: Int
    ): List<Giph>

}