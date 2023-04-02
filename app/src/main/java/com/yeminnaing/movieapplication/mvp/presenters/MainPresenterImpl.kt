package com.yeminnaing.movieapplication.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.yeminnaing.movieapplication.data.models.MovieModel
import com.yeminnaing.movieapplication.data.models.MovieModelImpl
import com.yeminnaing.movieapplication.data.vos.GenreVo
import com.yeminnaing.movieapplication.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {
    //View
    var mView: MainView? = null;


    //Model
    private val mMovieModel: MovieModel = MovieModelImpl

    //States
    private var mGenres: List<GenreVo>? = listOf()

    private lateinit var owner:LifecycleOwner

    override fun initView(view: MainView) {
        mView = view
    }


    override fun onUiReady(owner: LifecycleOwner) {
        this.owner=owner
        //NowPlaying
        mMovieModel.getNowPlayingMovie {
            mView?.showErrors(it)
        }?.observe(owner) {
            mView?.showNowPlayingMovie(it)
        }

        //PopularMovie
        mMovieModel.getPopularMovie {
            mView?.showErrors(it)
        }?.observe(owner) {
            mView?.showPopularMovie(it)
        }

        //TopRated
        mMovieModel.getTopRatedMovie {
            mView?.showErrors(it)
        }?.observe(owner) {
            mView?.showTopRatedMovie(it)
        }

        //Genre and  Get Movie From For First Genre
        mMovieModel.getGenre(
            { it ->
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let { firstGenreId ->
                    onTapGenre(firstGenreId)
                }
            }, {
                mView?.showErrors(it)
            }
        )

        //Get Actors
        mMovieModel.getActors({
            mView?.showActors(it)
        }, {
            mView?.showErrors(it)
        })
    }

    override fun onTapMovieFromBanner(movieId: Long) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovieFromShowCase(movieId: Long) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }

    override fun onTapMovie(movieId: Long) {
        mView?.navigateToMovieDetailsScreen(movieId)
    }


    override fun onTapGenre(genrePosition: Int) {
        mGenres?.getOrNull(genrePosition)?.id?.let { genreId ->
            mMovieModel?.getMovieByGenre(
                genreId = genreId.toString()
            ) { error ->
                mView?.showErrors(error)
            }?.observe(owner){
                mView?.showMovieByGenres(it)
            }
        }
    }
}