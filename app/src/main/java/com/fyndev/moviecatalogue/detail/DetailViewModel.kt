package com.fyndev.moviecatalogue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.BuildConfig
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    companion object {
        private const val apiKey = BuildConfig.MOVIE_KEY
    }

    private var id: Int = 0

    fun setSelectedById(id: Int) {
        this.id = id
    }

    fun getDetailMovie(): LiveData<MovieEntity> {
        return movieRepository.getDetailMovie(id, apiKey)
    }

    fun getDetailTvShow(): LiveData<TvShowEntity> {
        return movieRepository.getDetailTvShow(id, apiKey)
    }
}