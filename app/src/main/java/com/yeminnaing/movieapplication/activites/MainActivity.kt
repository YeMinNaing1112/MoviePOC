package com.yeminnaing.movieapplication.activites

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.yeminnaing.movieapplication.R
import com.yeminnaing.movieapplication.adapters.BannerAdapter
import com.yeminnaing.movieapplication.adapters.ShowCaseAdapter
import com.yeminnaing.movieapplication.data.vos.ActorVo
import com.yeminnaing.movieapplication.data.vos.GenreVo
import com.yeminnaing.movieapplication.data.vos.MovieVO
import com.yeminnaing.movieapplication.mvp.presenters.MainPresenter
import com.yeminnaing.movieapplication.mvp.presenters.MainPresenterImpl
import com.yeminnaing.movieapplication.mvp.views.MainView
import com.yeminnaing.movieapplication.viewpods.ActorsListViewPod
import com.yeminnaing.movieapplication.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {
    lateinit var mShowCaseAdapter: ShowCaseAdapter;
    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mBestPopularMovieListViewPot: MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorsListViewPod

    //Model
    //Data

    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()

        setUpToolbar()
        setUpBannerViewPager()
//      setUpGenreTabLayout()
        setUpListener()
        setUpShowCaseRecyclerView()
        setUpViewPod()

        mPresenter.onUiReady(this)

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    fun setUpViewPod() {
        mBestPopularMovieListViewPot = vpBestMoviePopularList as MovieListViewPod
        mBestPopularMovieListViewPot.setUpMovieListViewPod(mPresenter)
        mMovieByGenreViewPod = vpMovieByGenre as MovieListViewPod
        mMovieByGenreViewPod.setUpMovieListViewPod(mPresenter)

        mActorListViewPod = vpActorsHomeScreen as ActorsListViewPod
    }

    private fun setUpListener() {

        //SearchButton
        toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.btnSearch -> {
                    startActivity(Intent(applicationContext, MovieSearchActivity::class.java))
                    println("Bla")
                    false
                }
                else -> {
                    true
                }
            }
        }
        // Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPresenter.onTapGenre(tab?.position?:0)
//                    getMovieByGenreId(it)
            }
                override fun onTabUnselected(tab: TabLayout.Tab?) {

        }

                override fun onTabReselected(tab: TabLayout.Tab?) {

        }

    })
}

private fun setUpToolbar() {
    setSupportActionBar(toolBar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
}

private fun setUpBannerViewPager() {
    mBannerAdapter = BannerAdapter(mPresenter)
    viewPagerBanner.adapter = mBannerAdapter
    dotIndicatorBanner.attachTo(viewPagerBanner)

}

private fun setUpGenreTabLayout(genreList: List<GenreVo>) {

//        dummyGenreList.forEach {
//            val tab = tabLayoutGenre.newTab()
//            tab.text = it;
//            tabLayoutGenre.addTab(tab)
//        }

    genreList.forEach {
        tabLayoutGenre.newTab().apply {
            text = it.name
            tabLayoutGenre.addTab(this)
        }
    }
}

private fun setUpShowCaseRecyclerView() {
    mShowCaseAdapter = ShowCaseAdapter(mPresenter)
    rvShowCases.adapter = mShowCaseAdapter
    rvShowCases.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


}

override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_discover, menu)
    return true
}

override fun showNowPlayingMovie(nowPlayingMovies: List<MovieVO>) {
    mBannerAdapter.setNewData(nowPlayingMovies)
}

override fun showPopularMovie(popularMovies: List<MovieVO>) {
    mBestPopularMovieListViewPot.setData(popularMovies)
}

override fun showTopRatedMovie(topRatedMovies: List<MovieVO>) {
    mShowCaseAdapter.setNewData(topRatedMovies)
}

override fun showGenres(genreList: List<GenreVo>) {
    setUpGenreTabLayout(genreList)
}

override fun showMovieByGenres(movieByGenres: List<MovieVO>) {
    mMovieByGenreViewPod.setData(movieByGenres)
}

override fun showActors(actors: List<ActorVo>) {
    mActorListViewPod.setData(actors)
}

override fun navigateToMovieDetailsScreen(movieId: Long) {
    startActivity(MovieDetailsActivity.newIntent(this, movieId))
}

override fun showErrors(errorString: String) {
    Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
}

}