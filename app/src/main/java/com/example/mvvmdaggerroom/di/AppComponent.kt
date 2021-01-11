package com.example.mvvmdaggerroom.di

import com.example.mvvmdaggerroom.repository.TrendingRepository
import com.example.mvvmdaggerroom.view.ui.MainActivity
import com.example.mvvmdaggerroom.viewmodel.TrendingViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(trendingRepository: TrendingRepository)

    fun inject(viewModel: TrendingViewModel)

    fun inject(mainActivity: MainActivity)

}
