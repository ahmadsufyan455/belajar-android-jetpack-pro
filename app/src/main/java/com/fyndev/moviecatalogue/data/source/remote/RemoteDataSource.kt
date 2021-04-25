package com.fyndev.moviecatalogue.data.source.remote

import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse
import com.fyndev.moviecatalogue.service.ApiClient
import com.fyndev.moviecatalogue.service.ApiEndpoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiEndpoint: ApiEndpoint) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(endpoint: ApiEndpoint): RemoteDataSource =
                instance ?: synchronized(this) {
                    RemoteDataSource(endpoint).apply { instance = this }
                }
    }

    fun getMovies(apiKey: String, callback: LoadMovieCallback) {
        ApiClient.instance.getMovie(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onAllMovieReceived(it) }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // handle error
            }

        })
    }

    fun getTvShow(apiKey: String, callback: LoadTvShowCallback) {
        ApiClient.instance.getTvShow(apiKey).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onAllTvShowReceived(it) }
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                // handle error
            }

        })
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: MovieResponse)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: TvShowResponse)
    }
}