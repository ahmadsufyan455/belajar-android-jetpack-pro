package com.fyndev.moviecatalogue.home.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.BuildConfig
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    companion object {
        private const val apiKey = BuildConfig.MOVIE_KEY
    }

    fun getTvShow(): LiveData<TvShowResponse> = movieRepository.getTvShow(apiKey)

}