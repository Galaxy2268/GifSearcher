package com.galaxy.gifsearcher.di

import com.galaxy.gifsearcher.giflist.data.remote.GiphyApi
import com.galaxy.gifsearcher.giflist.data.repository.GifRepositoryImpl
import com.galaxy.gifsearcher.giflist.domain.repository.GifRepository
import com.galaxy.gifsearcher.giflist.domain.usecases.GetGifs
import com.galaxy.gifsearcher.giflist.domain.usecases.GifUseCases
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
    fun provideGifRepository(api: GiphyApi): GifRepository {
        return GifRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: GifRepository): GifUseCases{
        return GifUseCases(
            getGifs =  GetGifs(repository)
        )
    }



}