package com.example.mvvmdaggerroom

import android.app.Application
import com.example.mvvmdaggerroom.data.database.TrendingDatabase

class GiphyApplication:Application() {
    companion object {
        lateinit var instance: GiphyApplication
        lateinit var database: TrendingDatabase
    }

    init {
        instance=this;
    }
    override fun onCreate() {
        super.onCreate()
        database = TrendingDatabase.invoke(this)
    }
}