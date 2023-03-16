package com.yeminnaing.movieapplication.data.models

import android.content.Context
import com.yeminnaing.movieapplication.data.vos.*
import com.yeminnaing.movieapplication.network.dataagents.MovieDataAgent
import com.yeminnaing.movieapplication.network.dataagents.RetrofitDataAgentsImpl
import com.yeminnaing.movieapplication.persistence.MovieDataBase

object MovieModelImpl : MovieModel {

    //DataAgent
    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentsImpl

    //DataBase
    private var mMovieDataBase: MovieDataBase? = null

    fun initDataBase(context: Context) {
        mMovieDataBase = MovieDataBase.getDbInstance(context)
    }

    override fun getNowPlayingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        onSuccess(mMovieDataBase?.movieDao()?.getMovieByType(type = NOW_PLAYING) ?: listOf())

        mMovieDataAgent.getNowPlayingMovies(onSuccess = {
            it.forEach { movie -> movie.type = NOW_PLAYING }
            mMovieDataBase?.movieDao()?.insertMovie(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getPopularMovie(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

        onSuccess(mMovieDataBase?.movieDao()?.getMovieByType(type = POPULAR) ?: listOf())

        mMovieDataAgent.getPopularMovie(onSuccess = {
            it.forEach { movie -> movie.type = POPULAR }
            mMovieDataBase?.movieDao()?.insertMovie(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getTopRatedMovie(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

        onSuccess(mMovieDataBase?.movieDao()?.getMovieByType(type = TOP_RATED) ?: listOf())

        mMovieDataAgent.getTopRatedMovie(onSuccess = {
            it.forEach { movie -> movie.type = TOP_RATED }
            mMovieDataBase?.movieDao()?.insertMovie(it)
            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getGenre(onSuccess: (List<GenreVo>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getGenre(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getMovieByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieDataAgent.getMovieByGenre(
            genreId = genreId,
            onSuccess = {

                onSuccess((mMovieDataBase?.movieDao()?.getMovieByGenre(genres = genreId) ?: listOf()) as List<MovieVO>)

                it.forEach { movie->movie.genreType=genreId }
                mMovieDataBase?.movieDao()?.insertMovie(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )

    }

    override fun getActors(onSuccess: (List<ActorVo>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getActors(
            onSuccess = {
                       onSuccess( mMovieDataBase?.actorDao()?.getAllActors()?: listOf())

                mMovieDataBase?.actorDao()?.insertActors(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        //DataBase
        val movieFromDataBase = mMovieDataBase?.movieDao()?.getMovieById(movieId = movieId.toLong())
        movieFromDataBase?.let {
            onSuccess(it)
        }
        //NetWork
        mMovieDataAgent.getMovieDetail(
            movieId = movieId,
            onSuccess = {
                val movieFromDataBase =
                    mMovieDataBase?.movieDao()?.getMovieById(movieId = movieId.toLong())
                it.type = movieFromDataBase?.type
                mMovieDataBase?.movieDao()?.insertSingleMovie(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieDataAgent.getCreditByMovie(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}