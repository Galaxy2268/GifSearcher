package com.galaxy.gifsearcher.giflist.data.remote

import com.galaxy.gifsearcher.giflist.data.remote.util.GiphyKey.GIPHY_KEY
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphyApi {
    @GET("trending?api_key=$GIPHY_KEY")
    suspend fun getTrending(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response

    @GET("search?api_key=$GIPHY_KEY")
    suspend fun getWithSearch(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response

}