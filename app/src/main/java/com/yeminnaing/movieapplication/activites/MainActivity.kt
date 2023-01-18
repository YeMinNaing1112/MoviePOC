package com.yeminnaing.movieapplication.activites

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.yeminnaing.movieapplication.R
import com.yeminnaing.movieapplication.adapters.BannerAdapter
import com.yeminnaing.movieapplication.adapters.ShowCaseAdapter
import com.yeminnaing.movieapplication.delegates.BannerViewHolderDelegate
import com.yeminnaing.movieapplication.delegates.MovieViewHolderDelegate
import com.yeminnaing.movieapplication.delegates.ShowCaseViewHolderDelegate
import com.yeminnaing.movieapplication.dummy.dummyGenreList
import com.yeminnaing.movieapplication.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowCaseViewHolderDelegate,
    MovieViewHolderDelegate {
    lateinit var mShowCaseAdapter: ShowCaseAdapter;

    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mBestPopularMovieListViewPot: MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar()
        setUpBannerViewPager()
        setUpGenreTabLayout()
        setUpListener()
        setUpShowCaseRecyclerView()
        setUpViewPod()
    }

    fun setUpViewPod() {
        mBestPopularMovieListViewPot = vpBestMoviePopularList as MovieListViewPod
        mBestPopularMovieListViewPot.setUpMovieListViewPod(this)
        mMovieByGenreViewPod = vpMovieByGenre as MovieListViewPod
        mMovieByGenreViewPod.setUpMovieListViewPod(this)
    }

    private fun setUpListener() {
        // Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Snackbar.make(window.decorView, tab?.text ?: "", Snackbar.LENGTH_SHORT).show();
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
        mBannerAdapter = BannerAdapter(this)
        viewPagerBanner.adapter = mBannerAdapter
        dotIndicatorBanner.attachTo(viewPagerBanner)

    }

    private fun setUpGenreTabLayout() {

//        dummyGenreList.forEach {
//            val tab = tabLayoutGenre.newTab()
//            tab.text = it;
//            tabLayoutGenre.addTab(tab)
//        }

        dummyGenreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it
                tabLayoutGenre.addTab(this)
            }
        }
    }

    private fun setUpShowCaseRecyclerView() {
        mShowCaseAdapter = ShowCaseAdapter(this)
        rvShowCases.adapter = mShowCaseAdapter
        rvShowCases.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onTapMovieFromBanner() {
        startActivity(MovieDetailsActivity.newIntent(this))
    }

    override fun onTapMovieFromShowCase() {
        startActivity(MovieDetailsActivity.newIntent(this))
    }

    override fun onTapMovie() {
        startActivity(MovieDetailsActivity.newIntent(this))
    }
}