package com.nithin.tmdbappversion02.Adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.nithin.tmdbappversion02.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var moviePoster = itemView.findViewById<View>(R.id.moviePoster) as ImageView
    var movieName = itemView.findViewById<View>(R.id.movieName) as TextView
    var movieReleaseDate = itemView.findViewById<View>(R.id.movieReleaseDate) as TextView
    var movieRating = itemView.findViewById<View>(R.id.movieRating) as TextView
    var cardView = itemView.findViewById<View>(R.id.cardView) as CardView
}
