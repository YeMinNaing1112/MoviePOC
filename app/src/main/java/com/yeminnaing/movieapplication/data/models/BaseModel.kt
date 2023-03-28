package com.yeminnaing.movieapplication.data.models

import android.content.Context
import com.yeminnaing.movieapplication.network.TheMovieAPi
import com.yeminnaing.movieapplication.persistence.MovieDataBase
import com.yeminnaing.movieapplication.utils.BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {
    protected var mMovieApi: TheMovieAPi
    protected var mMovieDataBase: MovieDataBase? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()

        mMovieApi = retrofit.create(TheMovieAPi::class.java)
    }

    fun initDataBase(context: Context) {
        mMovieDataBase = MovieDataBase.getDbInstance(context)
    }
}