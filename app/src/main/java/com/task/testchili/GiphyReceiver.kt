package com.task.testchili

import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyReceiver {
    @GET("gifs/search?api_key=MgrMrP01Y7rWNslJgNfVVDT0QYqRyElw")
    //method that takes parameters for search and offset, and returns DataResult
    suspend fun searchGifs(
        @Query("q") query: String,
        @Query("offset") offset: Int
    ): DataResult


}