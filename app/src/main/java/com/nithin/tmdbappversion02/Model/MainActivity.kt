package com.nithin.tmdbappversion02.Model

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.nithin.tmdbappversion02.Adapter.MainActivityAdapter
import com.nithin.tmdbappversion02.R
import com.nithin.tmdbappversion02.RetrofitAPI.MovieNames
import com.nithin.tmdbappversion02.di.MovieListModule
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    private var mainActivityContract: MainActivityContract.Presenter = MovieListModule.getMovieListPresenter()
    private val movieNamesDataList = ArrayList<MovieNames>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityContract.attachView(this)
        mainActivityContract.loadMoviesList(movieNamesDataList)

        floatButtonScrollUp.setOnClickListener {
            recyclerViewMain.scrollToPosition(0)
            floatButtonScrollUp.hide()
        }

        recyclerViewMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) floatButtonScrollUp.show()
            }
        })

    }

    override fun displayMoviesList(movieNamesData: ArrayList<MovieNames>) {
        val adapter = MainActivityAdapter(this, movieNamesData)
        recyclerViewMain.adapter = adapter
        recyclerViewMain.layoutManager = GridLayoutManager(this@MainActivity, 2)
    }

    override fun displayError() {
        Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()
    }
}
