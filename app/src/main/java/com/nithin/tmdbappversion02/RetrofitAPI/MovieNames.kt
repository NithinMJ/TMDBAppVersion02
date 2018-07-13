package com.nithin.tmdbappversion02.RetrofitAPI

import com.google.gson.annotations.SerializedName

class MovieNames {
    @SerializedName("title")
    val movieTitle: String? = null

    @SerializedName("poster_path")
    val moviePoster: String? = null

    @SerializedName("release_date")
    val movieReleaseDate: String? = null

    @SerializedName("vote_average")
    val movieRating: String? = null

    @SerializedName("backdrop_path")
    val movieBackdrop: String? = null

    @SerializedName("original_language")
    val movieLanguage: String? = null

    @SerializedName("overview")
    val movieOverview: String? = null

}
