package com.nithin.tmdbappversion02.Model

import com.nithin.tmdbappversion02.RetrofitAPI.MovieNames

interface MainActivityContract {
    interface View {
        fun displayMoviesList(movieNamesData: ArrayList<MovieNames>)

        fun displayError()

    }

    interface Presenter {

        fun attachView(view: MainActivityContract.View)

        fun detachView()

        fun loadMoviesList(movieNamesDataList: ArrayList<MovieNames>)

    }
}