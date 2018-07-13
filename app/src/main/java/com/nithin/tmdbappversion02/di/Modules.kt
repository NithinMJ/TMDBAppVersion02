package com.nithin.tmdbappversion02.di

import com.nithin.tmdbappversion02.Model.MainActivityContract
import com.nithin.tmdbappversion02.Model.MainActivityPresenter
import com.nithin.tmdbappversion02.RetrofitAPI.MovieService
import com.nithin.tmdbappversion02.di.NetworkModule.API_KEY_VALUE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "http://api.themoviedb.org/3/movie/"
    const val API_KEY_VALUE = "33eaa774c3c9d738dbede19862118d3d"
    const val MOVIE_BACKDROP_URL = "http://image.tmdb.org/t/p/w780"

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getMovieService(): MovieService = retrofit.create(MovieService::class.java)
}

object MovieListModule {
    fun getMovieListPresenter(): MainActivityContract.Presenter =
            MainActivityPresenter(
                    NetworkModule.getMovieService(),
                    API_KEY_VALUE
            )
}