package com.yeminnaing.movieapplication.data.models

import androidx.lifecycle.LiveData
import com.yeminnaing.movieapplication.data.vos.ActorVo
import com.yeminnaing.movieapplication.data.vos.GenreVo
import com.yeminnaing.movieapplication.data.vos.MovieVO
import io.reactivex.rxjava3.core.Observable

interface MovieModel {
    fun getNowPlayingMovie(
        onFailure: (String) -> Unit,
    ): LiveData<List<MovieVO>>?

    fun getPopularMovie(
        onFailure: (String) -> Unit,
    ): LiveData<List<MovieVO>>?

    fun getTopRatedMovie(
        onFailure: (String) -> Unit,
    ): LiveData<List<MovieVO>>?

    fun getGenre(
        onSuccess: (List<GenreVo>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getMovieByGenre(
        genreId: String,
        onFailure: (String) -> Unit,
    ): LiveData<List<MovieVO>>?

    fun getActors(
        onSuccess: (List<ActorVo>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit,
    ): LiveData<MovieVO?>?

    fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
        onFailure: (String) -> Unit,
    )


    fun searchMovie(
        query: String
    ): Observable<List<MovieVO>>
}