package com.nithin.tmdbappversion02.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nithin.tmdbappversion02.MovieDetails.MovieDetailsActivity
import com.nithin.tmdbappversion02.MovieDetails.MovieDetailsInterface
import com.nithin.tmdbappversion02.MovieDetails.MovieDetailsPresenter
import com.nithin.tmdbappversion02.R
import com.nithin.tmdbappversion02.RetrofitAPI.MovieNames
import com.nithin.tmdbappversion02.di.NetworkModule
import com.squareup.picasso.Picasso

class MainActivityAdapter(context: Context, movie: ArrayList<MovieNames>) : RecyclerView.Adapter<MovieViewHolder>() {

    private var listMovieNames: List<MovieNames> = movie
    private var movieContext: Context = context
    private val movieDetailsInterface: MovieDetailsInterface = MovieDetailsPresenter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val inflater: View = LayoutInflater.from(movieContext).inflate(R.layout.movie_information_main, parent, false)

        return MovieViewHolder(inflater)

    }

    override fun getItemCount(): Int = listMovieNames.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieData: MovieNames = listMovieNames[position]

        Picasso.get()
                .load(NetworkModule.MOVIE_BACKDROP_URL + movieData.moviePoster)
                .placeholder(R.drawable.tmdb_icon)
                .into(holder.moviePoster)

        holder.movieReleaseDate.text = movieDetailsInterface.convertDateFormat(movieData.movieReleaseDate.toString())

        holder.movieName.text = movieData.movieTitle

        holder.movieRating.text = movieData.movieRating

        holder.cardView.setOnClickListener {
            val intent = Intent(movieContext.applicationContext, MovieDetailsActivity::class.java)
            intent.putExtra("movie_backdrop",movieData.movieBackdrop)
            intent.putExtra("movie_title",movieData.movieTitle)
            intent.putExtra("movie_poster_path",movieData.moviePoster)
            intent.putExtra("movie_release_date",movieData.movieReleaseDate)
            intent.putExtra("movie_vote_average",movieData.movieRating)
            intent.putExtra("movie_original_language",movieData.movieLanguage)
            intent.putExtra("movie_overview",movieData.movieOverview)

            movieContext.startActivity(intent)

        }

    }

}