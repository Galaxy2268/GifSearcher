package com.galaxy.gifsearcher.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.galaxy.gifsearcher.giflist.data.remote.GiphyApi
import com.galaxy.gifsearcher.giflist.data.remote.GiphyPagingSource
import com.galaxy.gifsearcher.giflist.data.repository.GifRepositoryImpl
import com.galaxy.gifsearcher.giflist.data.remote.Response
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import com.galaxy.gifsearcher.giflist.domain.repository.GifRepository
import com.galaxy.gifsearcher.util.Constants.GIPHY_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideGiphyApi(): GiphyApi{
        return Retrofit.Builder()
            .baseUrl(GIPHY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GiphyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGiphyPager(api: GiphyApi): Pager<Int, Gif> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {GiphyPagingSource(api)},
        )
    }

    @Provides
    @Singleton
    fun provideGifRepository(pager: Pager<Int, Gif>): GifRepository {
        return GifRepositoryImpl(pager)
    }



}