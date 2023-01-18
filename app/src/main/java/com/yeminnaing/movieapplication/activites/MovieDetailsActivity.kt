package com.yeminnaing.movieapplication.activites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yeminnaing.movieapplication.R
import com.yeminnaing.movieapplication.viewpods.ActorsListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MovieDetailsActivity::class.java)
        }
    }

    //viewPods
    lateinit var actorsViewPod: ActorsListViewPod
    lateinit var creatorsViewPod: ActorsListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setUpViewpod()
        setUpListener()
    }

    private fun setUpListener() {
        btnBack.setOnClickListener{
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
}