package com.example.mvvmdaggerroom.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmdaggerroom.di.DaggerAppComponent
import com.example.mvvmdaggerroom.repository.TrendingRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TrendingViewModel:ViewModel() {
    @Inject
    lateinit var repository: TrendingRepository

    private val compositeDisposable by lazy { CompositeDisposable() }

    init {
        DaggerAppComponent.create().inject(this)
        compositeDisposable.add(repository.fetchDataFromDatabase())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}