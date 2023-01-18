package com.yeminnaing.movieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yeminnaing.movieapplication.delegates.ShowCaseViewHolderDelegate

class ShowCaseViewHolder(itemView: View,private val mDelegate: ShowCaseViewHolderDelegate) :RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            mDelegate.onTapMovieFromShowCase()
        }
    }
}