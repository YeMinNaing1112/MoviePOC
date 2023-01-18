package com.yeminnaing.movieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yeminnaing.movieapplication.delegates.BannerViewHolderDelegate

class BannerViewHolder(itemView: View,private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            mDelegate.onTapMovieFromBanner()
        }
    }
}