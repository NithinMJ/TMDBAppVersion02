package com.nithin.tmdbappversion02.Model

import com.nithin.tmdbappversion02.BaseTest
import com.nithin.tmdbappversion02.RetrofitAPI.MovieNames
import com.nithin.tmdbappversion02.RetrofitAPI.MovieNamesList
import com.nithin.tmdbappversion02.RetrofitAPI.MovieService
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivityPresenterTest : BaseTest() {

    private val mockMovieService = mock(MovieService::class.java)
    private val mockApiKey = "some-key"
    private val mockView = mock(MainActivityContract.View::class.java)
    private val mockCall: Call<Any> = mock()
    private val movieList: ArrayList<MovieNames> = arrayListOf()

    private val presenter = MainActivityPresenter(
            mockMovieService,
            mockApiKey
    )

    @Before
    fun setup() {
        presenter.attachView(mockView)
    }

    @Test
    fun whenFindPopularIsCalledThenMovieServiceWillSucceedWithError() {
        // Arrange

        given(mockMovieService.findPopular(mockApiKey))
                .willReturn(mockCall as Call<MovieNamesList>)
        arrangeApiCallSuccessfulWithError()

        // Act
        presenter.loadMoviesList(movieList)

        // Assert
        verify(mockView).displayError()
    }

    @Test
    fun whenFindPopularIsCalledThenMovieServiceWillSucceedWithResponse() {
        //Arrange
        given(mockMovieService.findPopular(mockApiKey))
                .willReturn(mockCall as Call<MovieNamesList>)
        arrangeApiCallSuccessful(responseBody = MovieNamesList().apply { movieData = arrayListOf() })

        //Act
        presenter.loadMoviesList(movieList)

        //Assert
        verify(mockView).displayMoviesList(movieList)

    }

    @Test
    fun whenFindPopularIsCalledThenMovieServiceWillFail() {
        //Arrange

        given(mockMovieService.findPopular(mockApiKey))
                .willReturn(mockCall as Call<MovieNamesList>)
        arrangeApiCallFailed()

        //Act
        presenter.loadMoviesList(movieList)

        //Assert
        verify(mockView).displayError()
    }

    @Test
    fun whenFindPopularIsCalledThenMovieServiceWillSucceedWithNullBody() {
        //Arrange

        given(mockMovieService.findPopular(mockApiKey))
                .willReturn(mockCall as Call<MovieNamesList>)
        arrangeApiCallSuccessfulWithNullBody()

        //Act
        presenter.loadMoviesList(movieList)

        //Assert
        verify(mockView).displayError()
    }

    private fun <T> arrangeApiCallSuccessful(responseBody: T) {
        Mockito.doAnswer { invocation ->
            val callback: Callback<Any> = invocation.getArgument(0)

            callback.onResponse(
                    mockCall,
                    Response.success(responseBody)
            )

            return@doAnswer null
        }.`when`(mockCall).enqueue(ArgumentMatchers.any(getType()))

    }

    private fun arrangeApiCallSuccessfulWithNullBody() {
        Mockito.doAnswer { invocation ->
            val callback: Callback<Any> = invocation.getArgument(0)

            callback.onResponse(
                    mockCall,
                    Response.success(null)
            )

            return@doAnswer null
        }.`when`(mockCall).enqueue(ArgumentMatchers.any(getType()))
    }

    private fun arrangeApiCallSuccessfulWithError() {
        Mockito.doAnswer { invocation ->
            val callback: Callback<Any> = invocation.getArgument(0)

            callback.onResponse(
                    mockCall,
                    Response.error(
                            500,
                            ResponseBody.create(
                                    MediaType.parse("application/json"),
                                    "{}"
                            )
                    )
            )

            return@doAnswer null
        }.`when`(mockCall).enqueue(ArgumentMatchers.any(getType()))
    }

    private fun arrangeApiCallFailed() {
        Mockito.doAnswer { invocation ->
            val callback: Callback<Any> = invocation.getArgument(0)

            callback.onFailure(mockCall, IOException())

            return@doAnswer null
        }.`when`(mockCall).enqueue(ArgumentMatchers.any(getType()))
    }

}