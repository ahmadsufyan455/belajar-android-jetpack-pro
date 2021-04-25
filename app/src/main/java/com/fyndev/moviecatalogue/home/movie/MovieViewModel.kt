package com.fyndev.moviecatalogue.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    companion object {
        private const val apiKey = "e40c34a2a097d56ae9509a5ab8c47d44"
    }

    fun getDataMovie(): LiveData<MovieResponse> = movieRepository.getMovies(apiKey)

}