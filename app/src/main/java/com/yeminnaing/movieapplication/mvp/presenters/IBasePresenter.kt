package com.yeminnaing.movieapplication.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {
    fun onUiReady(owner:LifecycleOwner)
}