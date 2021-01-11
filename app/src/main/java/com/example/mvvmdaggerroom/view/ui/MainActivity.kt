package com.example.mvvmdaggerroom.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.mvvmdaggerroom.R
import com.example.mvvmdaggerroom.di.DaggerAppComponent
import com.example.mvvmdaggerroom.view.adapter.TrendingAdapter
import com.example.mvvmdaggerroom.viewmodel.TrendingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var trendingAdapter: TrendingAdapter

    private val viewModel: TrendingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerAppComponent.create().inject(this)

        setUpRecyclerView()

        observeLiveData()
    }
    private fun setUpRecyclerView() {
        recycler_view.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = trendingAdapter
        }
    }
    private fun observeLiveData() {
        observeInProgress()
        observeIsError()
        observeGiphyList()
    }
    private fun observeInProgress() {
        viewModel.repository.isInProgress.observe(this, Observer { isLoading ->
            isLoading.let {
                if (it) {
                    empty_text.visibility = View.GONE
                    recycler_view.visibility = View.GONE
                    fetch_progress.visibility = View.VISIBLE
                } else {
                    fetch_progress.visibility = View.GONE
                }
            }
        })
    }

    private fun observeIsError() {
        viewModel.repository.isError.observe(this, Observer { isError ->
            isError.let {
                if (it) {
                    disableViewsOnError()
                } else {
                    empty_text.visibility = View.GONE
                    fetch_progress.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun disableViewsOnError() {
        fetch_progress.visibility = View.VISIBLE
        empty_text.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
        trendingAdapter.setUpData(emptyList())
        fetch_progress.visibility = View.GONE
    }

    private fun observeGiphyList() {
        viewModel.repository.data.observe(this, Observer { giphies ->
            giphies.let {
                if (it != null && it.isNotEmpty()) {
                    fetch_progress.visibility = View.VISIBLE
                    recycler_view.visibility = View.VISIBLE
                    trendingAdapter.setUpData(it)
                    empty_text.visibility = View.GONE
                    fetch_progress.visibility = View.GONE
                } else {
                    disableViewsOnError()
                }
            }
        })
    }

}