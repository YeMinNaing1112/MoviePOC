package com.yeminnaing.movieapplication.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.yeminnaing.movieapplication.data.models.MovieModelImpl
import com.yeminnaing.movieapplication.mvp.views.MovieDetailsView

class MovieDetailsPresenterImpl : ViewModel(), MovieDetailsPresenter {

    //Model
    private val mMovieModel = MovieModelImpl

    //View
    private var mView: MovieDetailsView? = null

    override fun initView(view: MovieDetailsView) {
        mView = view
    }

    override fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Long) {
        //MovieDetails
        mMovieModel.getMovieDetails(movieId.toString()) {
            mView?.showErrors(it)
        }?.observe(owner) {
            it?.let {
                mView?.showMovieDetail(it)
            }
        }

        //Credits
        mMovieModel.getCreditByMovie(movieId = movieId.toString(), {
            mView?.showCreditsByMovie(cast = it.first, crew = it.second)
        }, {
            mView?.showErrors(it)
        })

    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {}
}