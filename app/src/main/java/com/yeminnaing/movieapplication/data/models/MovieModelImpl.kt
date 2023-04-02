package com.yeminnaing.movieapplication.data.models

import androidx.lifecycle.LiveData
import com.yeminnaing.movieapplication.data.vos.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BaseModel(), MovieModel {

    //DataAgent
//    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentsImpl

    //DataBase
//    private var mMovieDataBase: MovieDataBase? = null


    override fun getNowPlayingMovie(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        //Network
        mMovieApi.getNowPlayingMovie(page = 1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                it.result?.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDataBase?.movieDao()?.insertMovie(it.result ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDataBase?.movieDao()?.getMovieByType(type = NOW_PLAYING)
    }

    override fun getPopularMovie(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        //Network
        mMovieApi.getPopularMovie(page = 1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                it.result?.forEach { movie -> movie.type = POPULAR }
                mMovieDataBase?.movieDao()?.insertMovie(it.result ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDataBase?.movieDao()?.getMovieByType(type = POPULAR)

    }

    override fun getTopRatedMovie(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {

        //NetWork
        mMovieApi.getTopRatedMovie(page = 1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                it.result?.forEach { movie -> movie.type = TOP_RATED }
                mMovieDataBase?.movieDao()?.insertMovie(it.result ?: listOf())

            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDataBase?.movieDao()?.getMovieByType(type = TOP_RATED)
    }

    override fun getGenre(onSuccess: (List<GenreVo>) -> Unit, onFailure: (String) -> Unit) {

        mMovieApi.getGenre().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.genres)
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }


    override fun getMovieByGenre(
        genreId: String,
        onFailure: (String) -> Unit,
    ): LiveData<List<MovieVO>>? {
        mMovieApi.getMovieByGenre(genreId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                it.result.forEach { movie -> movie.genreType = genreId }
                mMovieDataBase?.movieDao()?.insertMovie(it.result)
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDataBase?.movieDao()?.getMovieByGenre(genres = genreId)
    }

    override fun getActors(onSuccess: (List<ActorVo>) -> Unit, onFailure: (String) -> Unit) {
        onSuccess(mMovieDataBase?.actorDao()?.getAllActors() ?: listOf())

        mMovieApi.getActors(page = 1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                mMovieDataBase?.actorDao()?.insertActors(it.results)
                onSuccess(it.results)
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit,
    ): LiveData<MovieVO?>? {
        //DataBase
        //NetWork

        mMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                val movieFromDataBase =
                    mMovieDataBase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toLong())
                it.type = movieFromDataBase?.type
                mMovieDataBase?.movieDao()?.insertSingleMovie(it)
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDataBase?.movieDao()?.getMovieById(movieId = movieId.toLong())
    }

    override fun getCreditByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVo>, List<ActorVo>>) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mMovieApi.getCreditByMovie(movieId = movieId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                onSuccess(Pair(it.cast, it.crew))
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    override fun searchMovie(query: String): Observable<List<MovieVO>> {
        return mMovieApi
            .searchMovie(query = query)
            .map { it.result ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }
}