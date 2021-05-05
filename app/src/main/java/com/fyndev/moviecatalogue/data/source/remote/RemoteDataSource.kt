package com.fyndev.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyndev.moviecatalogue.BuildConfig.MOVIE_KEY
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse
import com.fyndev.moviecatalogue.service.ApiClient
import com.fyndev.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }
    }

    /*
     * List
     */
    fun getMovies(): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieResponse>>()
        ApiClient.instance.getMovie(MOVIE_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { resultMovie.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // handle error
            }

        })
        return resultMovie
    }

    fun getTvShow(): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<TvShowResponse>>()
        ApiClient.instance.getTvShow(MOVIE_KEY).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { resultTvShow.value = ApiResponse.success(it) }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                // handle error
            }

        })
        return resultTvShow
    }

    /*
     * Detail
     */
    fun getDetailMovie(id: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        ApiClient.instance.getDetailMovie(id, MOVIE_KEY)
            .enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    response: Response<DetailMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { resultDetailMovie.value = ApiResponse.success(it) }
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    // handle error
                }

            })
        return resultDetailMovie
    }

    fun getDetailTvShow(id: Int): LiveData<ApiResponse<DetailTvShowResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        ApiClient.instance.getDetailTvShow(id, MOVIE_KEY)
            .enqueue(object : Callback<DetailTvShowResponse> {
                override fun onResponse(
                    call: Call<DetailTvShowResponse>,
                    response: Response<DetailTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { resultDetailTvShow.value = ApiResponse.success(it) }
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                    // handle error
                }

            })
        return resultDetailTvShow
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: MovieResponse)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: TvShowResponse)
    }

    interface LoadDetailMovieCallback {
        fun onDetailReceived(movieEntity: MovieEntity)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailReceived(tvShowEntity: TvShowEntity)
    }
}