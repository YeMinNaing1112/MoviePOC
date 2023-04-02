package com.yeminnaing.movieapplication.mvp.views

import com.yeminnaing.movieapplication.data.vos.ActorVo
import com.yeminnaing.movieapplication.data.vos.GenreVo
import com.yeminnaing.movieapplication.data.vos.MovieVO

interface MainView :BaseView{
    fun showNowPlayingMovie(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovie(popularMovies:List<MovieVO>)
    fun showTopRatedMovie(topRatedMovies:List<MovieVO>)
    fun showGenres(genreList: List<GenreVo>)
    fun showMovieByGenres(movieByGenres:List<MovieVO>)
    fun showActors(actors:List<ActorVo>)
    fun navigateToMovieDetailsScreen(movieId: Long)
}