package com.yeminnaing.movieapplication.activites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.yeminnaing.movieapplication.R
import com.yeminnaing.movieapplication.data.vos.ActorVo
import com.yeminnaing.movieapplication.data.vos.GenreVo
import com.yeminnaing.movieapplication.data.vos.MovieVO
import com.yeminnaing.movieapplication.mvp.presenters.MovieDetailsPresenter
import com.yeminnaing.movieapplication.mvp.presenters.MovieDetailsPresenterImpl
import com.yeminnaing.movieapplication.mvp.views.MovieDetailsView
import com.yeminnaing.movieapplication.utils.IMAGE_BASE_URL
import com.yeminnaing.movieapplication.viewpods.ActorsListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : BaseActivity(), MovieDetailsView {
    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun newIntent(context: Context, movieId: Long): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    //viewPods
    lateinit var actorsViewPod: ActorsListViewPod
    lateinit var creatorsViewPod: ActorsListViewPod

    //presenter
    private lateinit var mPresenter: MovieDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setUpPresenter()
        setUpViewpod()
        setUpListener()
        val movieId = intent?.getLongExtra(EXTRA_MOVIE_ID, 0)

        movieId?.let {
            mPresenter.onUiReadyInMovieDetails(this, movieId)
        }


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MovieDetailsPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    private fun setUpListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewpod() {
        actorsViewPod = vpActors as ActorsListViewPod
        actorsViewPod.setUpActorViewPod(
            backGroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        creatorsViewPod = vpCreators as ActorsListViewPod
        creatorsViewPod.setUpActorViewPod(
            backGroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }


    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieDetail)

        tvMovieName.text = movie.title ?: ""
        tvMovieReleaseYear.text = movie.releaseDate?.substring(0, 4)
        tvRating.text = movie.voteAverage?.toString() ?: ""
        movie.voteCount?.let {
            tvNumberOfVotes.text = "$it VOTES"
        }
        rbRatingMovieDetail.rating = movie.getRatingBaseOnFiveStar()

        bindGenre(movie, movie.genres ?: listOf())

        tvOverView.text = movie.overView ?: ""
        tvOriginalTitle.text = movie.title ?: ""
        tvType.text = movie.getGenresAsCommaSeparatedString()

        tvProduction.text = movie.getProductionCountriesAsCommaSeparated()
        tvPremiere.text = movie.releaseDate ?: ""
        tvDescription.text = movie.overView ?: ""
    }

    private fun bindGenre(
        movie: MovieVO,
        genre: List<GenreVo>,
    ) {
        movie.genres?.count()?.let {
            tvFistGenre.text = genre.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genre.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genre.getOrNull(2)?.name ?: ""

        }
    }

    override fun showMovieDetail(movie: MovieVO) {
        bindData(movie)
    }

    override fun showCreditsByMovie(cast: List<ActorVo>, crew: List<ActorVo>) {
        actorsViewPod.setData(cast)
        creatorsViewPod.setData(crew)
    }

    override fun navigateBack() {
        finish()
    }

    override fun showErrors(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

}