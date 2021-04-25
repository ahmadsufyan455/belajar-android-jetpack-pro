package com.fyndev.moviecatalogue.data.source.remote

import android.util.Log
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse
import com.fyndev.moviecatalogue.service.ApiClient
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

    /*
     * Detail
     */
    fun getDetailMovie(id: Int, apiKey: String, callback: LoadDetailMovieCallback) {
        ApiClient.instance.getDetailMovie(id, apiKey).enqueue(object : Callback<MovieEntity> {
            override fun onResponse(call: Call<MovieEntity>, response: Response<MovieEntity>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onDetailReceived(it) }
                }
            }

            override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                // handle error
            }

        })
    }

    fun getDetailTvShow(id: Int, apiKey: String, callback: LoadDetailTvShowCallback) {
        ApiClient.instance.getDetailTvShow(id, apiKey).enqueue(object : Callback<TvShowEntity> {
            override fun onResponse(call: Call<TvShowEntity>, response: Response<TvShowEntity>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onDetailReceived(it) }
                }
            }

            override fun onFailure(call: Call<TvShowEntity>, t: Throwable) {
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

    interface LoadDetailMovieCallback {
        fun onDetailReceived(movieEntity: MovieEntity)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailReceived(tvShowEntity: TvShowEntity)
    }
}