package com.yeminnaing.movieapplication.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.yeminnaing.movieapplication.mvp.views.MovieDetailsView

interface MovieDetailsPresenter : IBasePresenter {
    fun initView(view: MovieDetailsView)
    fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId:Long)
    fun onTapBack()
}