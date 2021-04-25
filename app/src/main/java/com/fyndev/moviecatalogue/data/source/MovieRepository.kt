package com.fyndev.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyndev.moviecatalogue.data.source.remote.RemoteDataSource
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieRepository(remoteData).apply { instance = this }
                }
    }

    override fun getMovies(apiKey: String): LiveData<MovieResponse> {
        val movies = MutableLiveData<MovieResponse>()
        remoteDataSource.getMovies(apiKey, object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: MovieResponse) {
                movies.postValue(movieResponse)
            }

        })
        return movies
    }

    override fun getTvShow(apiKey: String): LiveData<TvShowResponse> {
        val tvShow = MutableLiveData<TvShowResponse>()
        remoteDataSource.getTvShow(apiKey, object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: TvShowResponse) {
                tvShow.postValue(tvShowResponse)
            }

        })
        return tvShow
    }
}