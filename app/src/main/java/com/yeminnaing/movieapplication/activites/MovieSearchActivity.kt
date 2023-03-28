package com.yeminnaing.movieapplication.activites

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.yeminnaing.movieapplication.R
import com.yeminnaing.movieapplication.adapters.MovieAdapter
import com.yeminnaing.movieapplication.data.models.MovieModel
import com.yeminnaing.movieapplication.data.models.MovieModelImpl
import com.yeminnaing.movieapplication.delegates.MovieViewHolderDelegate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_search.*
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(), MovieViewHolderDelegate {
    lateinit var mMovieAdapter: MovieAdapter
    private val mMovieModel: MovieModel = MovieModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)
        setUpRecyclerView()
        setUpListener()
    }

    private fun setUpListener() {
        etSearch.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovie(it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)
            }, {
                showErrors(it.localizedMessage ?: "")
            })

    }

    private fun showErrors(errors: String) {
        Toast.makeText(this, errors, Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        mMovieAdapter = MovieAdapter(this)
        rvMovies.adapter = mMovieAdapter
        rvMovies.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onTapMovie(movieId: Long) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId))
    }
}