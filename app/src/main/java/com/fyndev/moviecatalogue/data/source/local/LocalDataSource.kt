package com.fyndev.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getMovies(): LiveData<List<MovieEntity>> = movieDao.getMovies()

    fun getTvShows(): LiveData<List<TvShowEntity>> = movieDao.getTvShows()

    fun getMovieById(id: Int): LiveData<MovieEntity> = movieDao.getMovieById(id)

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = movieDao.getTvShowById(id)

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = movieDao.insertTvShows(tvShows)

    fun updateMovie(movie: MovieEntity) {
        movieDao.updateMovie(movie)
    }

    fun updateTvShow(tvShow: TvShowEntity) {
        movieDao.updateTvShow(tvShow)
    }

    fun getFavoriteMovie() = movieDao.getFavoriteMovie()

    fun setMovieStatus(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateMovie(movie)
    }

    fun getFavoriteTvShow() = movieDao.getFavoriteTvShow()

    fun setTvShowStatus(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        movieDao.updateTvShow(tvShow)
    }
}