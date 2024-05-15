package com.galaxy.gifsearcher.di

import com.galaxy.gifsearcher.giflist.data.remote.GiphyApi
import com.galaxy.gifsearcher.giflist.data.repository.FakeRepository
import com.galaxy.gifsearcher.giflist.domain.repository.GifRepository
import com.galaxy.gifsearcher.giflist.domain.usecases.GetGifs
import com.galaxy.gifsearcher.giflist.domain.usecases.GifUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {


    @Provides
    @Singleton
    fun provideGifRepository(api: GiphyApi): GifRepository {
        return FakeRepository()
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: GifRepository): GifUseCases {
        return GifUseCases(
            getGifs =  GetGifs(repository)
        )
    }



}