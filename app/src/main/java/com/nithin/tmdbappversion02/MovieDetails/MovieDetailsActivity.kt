package com.nithin.tmdbappversion02.MovieDetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nithin.tmdbappversion02.R
import com.nithin.tmdbappversion02.di.NetworkModule
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private val movieDetailsInterface: MovieDetailsInterface = MovieDetailsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieBackdrop = intent.getStringExtra("movie_backdrop")
        val movieTitle = intent.getStringExtra("movie_title")
        val moviePoster = intent.getStringExtra("movie_poster_path")
        val movieReleaseDate = intent.getStringExtra("movie_release_date")
        val movieRating = intent.getStringExtra("movie_vote_average")
        val movieLanguage = intent.getStringExtra("movie_original_language")
        val movieOverview = intent.getStringExtra("movie_overview")

        Picasso.get()
                .load(NetworkModule.MOVIE_BACKDROP_URL + movieBackdrop)
                .into(movieBackdropImage)

        movieTitleText.text = movieTitle

        Picasso.get()
                .load(NetworkModule.MOVIE_BACKDROP_URL + moviePoster)
                .placeholder(R.drawable.tmdb_icon)
                .into(moviePosterImage)

        movieReleaseDateText.text = movieDetailsInterface.convertDateFormat(movieReleaseDate)

        movieRatingText.text = movieRating

        movieLanguageText.text = movieLanguage

        movieOverviewText.text = movieOverview


    }
}
