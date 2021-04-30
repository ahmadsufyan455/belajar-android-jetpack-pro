package com.fyndev.moviecatalogue.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.BuildConfig
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    companion object {
        private const val apiKey = BuildConfig.MOVIE_KEY
    }

    fun getDataMovie(): LiveData<MovieResponse> = movieRepository.getMovies(apiKey)

}