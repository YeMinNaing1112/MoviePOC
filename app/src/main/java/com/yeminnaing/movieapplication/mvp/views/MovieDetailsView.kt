package com.yeminnaing.movieapplication.mvp.views

import com.yeminnaing.movieapplication.data.vos.ActorVo
import com.yeminnaing.movieapplication.data.vos.MovieVO

interface MovieDetailsView : BaseView {
    fun showMovieDetail(movie: MovieVO)
    fun showCreditsByMovie(cast:List<ActorVo>,crew:List<ActorVo>)
    fun navigateBack()
}