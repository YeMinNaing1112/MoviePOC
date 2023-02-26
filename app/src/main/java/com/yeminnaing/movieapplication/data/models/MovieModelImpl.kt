package com.yeminnaing.movieapplication.data.models

import com.yeminnaing.movieapplication.data.vos.ActorVo
import com.yeminnaing.movieapplication.data.vos.GenreVo
import com.yeminnaing.movieapplication.data.vos.MovieVO
import com.yeminnaing.movieapplication.network.dataagents.MovieDataAgent
import com.yeminnaing.movieapplication.network.dataagents.RetrofitDataAgentsImpl

object MovieModelImpl : MovieModel {

    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentsImpl
    override fun getNowPlayingMovie(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getPopularMovie(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getPopularMovie(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getTopRatedMovie(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getTopRatedMovie(onSuccess = onSuccess, onFailure = onFailure)
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
            onSuccess = onSuccess,
            onFailure = onFailure
        )

    }

    override fun getActors(onSuccess: (List<ActorVo>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getActors(
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit,
    ) {
      mMovieDataAgent.getMovieDetail(
          movieId=movieId,
          onSuccess=onSuccess,
          onFailure=onFailure
      )
    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieDataAgent.getCreditByMovie(
            movieId=movieId,
            onSuccess=onSuccess,
            onFailure=onFailure
        )
    }
}