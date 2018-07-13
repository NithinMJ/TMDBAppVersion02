package com.nithin.tmdbappversion02.RetrofitAPI

import com.google.gson.annotations.SerializedName

class MovieNamesList {

    @SerializedName("results")
    var movieData: ArrayList<MovieNames>? = null

}
