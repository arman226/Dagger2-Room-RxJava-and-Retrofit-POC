package com.example.mvvmdaggerroom.data.network

import com.example.mvvmdaggerroom.model.TrendingResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("v1/gifs/trending")
    fun getTrending(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: String,
        @Query("rating") rating: String
    ): Flowable<TrendingResult>
}