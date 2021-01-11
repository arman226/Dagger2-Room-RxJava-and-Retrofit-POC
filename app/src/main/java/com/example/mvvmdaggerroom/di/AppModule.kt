package com.example.mvvmdaggerroom.di

import com.example.mvvmdaggerroom.data.network.GiphyApi
import com.example.mvvmdaggerroom.data.network.GiphyApiService
import com.example.mvvmdaggerroom.model.Data
import com.example.mvvmdaggerroom.repository.TrendingRepository
import com.example.mvvmdaggerroom.view.adapter.TrendingAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideApi(): GiphyApi = GiphyApiService.getClient()
    @Provides
    fun provideTrendingRepository() = TrendingRepository()

    @Provides
    fun provideListData() = ArrayList<Data>()

    @Provides
    fun provideTrendingAdapter(data: ArrayList<Data>): TrendingAdapter = TrendingAdapter(data)
}