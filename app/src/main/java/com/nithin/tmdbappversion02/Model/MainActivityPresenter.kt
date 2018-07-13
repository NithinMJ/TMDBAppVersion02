package com.nithin.tmdbappversion02.Model

import com.nithin.tmdbappversion02.RetrofitAPI.MovieNames
import com.nithin.tmdbappversion02.RetrofitAPI.MovieNamesList
import com.nithin.tmdbappversion02.RetrofitAPI.MovieService
import retrofit2.Call
import retrofit2.Response

class MainActivityPresenter(
        private val movieService: MovieService,
        private val apiKey: String
) : MainActivityContract.Presenter {

    private var view: MainActivityContract.View? = null

    override fun attachView(view: MainActivityContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun loadMoviesList(movieNamesDataList: ArrayList<MovieNames>) {
        movieService.findPopular(apiKey)
                .enqueue(object : retrofit2.Callback<MovieNamesList> {
                    override fun onFailure(call: Call<MovieNamesList>?, t: Throwable?) {
                        view?.displayError()
                    }

                    override fun onResponse(call: Call<MovieNamesList>?, response: Response<MovieNamesList>?) {
                        if (response != null && response.isSuccessful) {
                            val movieResponseData: ArrayList<MovieNames>? = response.body()?.movieData

                            if (movieResponseData != null) {
                                movieNamesDataList.addAll(movieResponseData)
                                view?.displayMoviesList(movieNamesDataList)
                            } else {
                                view?.displayError()
                            }
                        } else {
                            view?.displayError()
                        }
                    }
                })
    }

}

