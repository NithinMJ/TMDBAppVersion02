package com.nithin.tmdbappversion02.RetrofitAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("popular")
    fun findPopular(
            @Query("api_key") apiKey:String
//            @Query("page") pageNumber: Int
    ): Call<MovieNamesList>

}
