package com.galaxy.gifsearcher.giflist.data.remote

import retrofit2.http.GET


interface GiphApi {
    @GET
    suspend fun getTrending()

    @GET
    suspend fun getWithSearch(

    )

}