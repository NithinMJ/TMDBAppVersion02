package com.nithin.tmdbappversion02.MovieDetails

import java.text.SimpleDateFormat
import java.util.*

class MovieDetailsPresenter : MovieDetailsInterface {

    override fun convertDateFormat(releaseDate: String): String {
        return SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(releaseDate))
    }
}
