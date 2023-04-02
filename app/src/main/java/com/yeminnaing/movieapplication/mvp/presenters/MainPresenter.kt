package com.yeminnaing.movieapplication.mvp.presenters

import com.yeminnaing.movieapplication.delegates.BannerViewHolderDelegate
import com.yeminnaing.movieapplication.delegates.MovieViewHolderDelegate
import com.yeminnaing.movieapplication.delegates.ShowCaseViewHolderDelegate
import com.yeminnaing.movieapplication.mvp.views.MainView

interface MainPresenter : IBasePresenter, BannerViewHolderDelegate, ShowCaseViewHolderDelegate,
    MovieViewHolderDelegate {
        fun initView(view : MainView)
        fun onTapGenre(genrePosition: Int)
}