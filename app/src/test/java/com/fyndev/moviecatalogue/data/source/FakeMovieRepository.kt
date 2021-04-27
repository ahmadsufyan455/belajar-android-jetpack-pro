package com.fyndev.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.data.source.remote.RemoteDataSource
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

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

    override fun getDetailMovie(id: Int, apiKey: String): LiveData<MovieEntity> {
        val detailMovie = MutableLiveData<MovieEntity>()
        remoteDataSource.getDetailMovie(id, apiKey, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailReceived(movieEntity: MovieEntity) {
                detailMovie.postValue(movieEntity)
            }

        })
        return detailMovie
    }

    override fun getDetailTvShow(id: Int, apiKey: String): LiveData<TvShowEntity> {
        val detailTvShow = MutableLiveData<TvShowEntity>()
        remoteDataSource.getDetailTvShow(id, apiKey, object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailReceived(tvShowEntity: TvShowEntity) {
                detailTvShow.postValue(tvShowEntity)
            }

        })
        return detailTvShow
    }
}