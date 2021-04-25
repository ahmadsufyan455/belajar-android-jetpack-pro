package com.fyndev.moviecatalogue.home.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    companion object {
        private const val apiKey = "e40c34a2a097d56ae9509a5ab8c47d44"
    }

    fun getTvShow(): LiveData<TvShowResponse> = movieRepository.getTvShow(apiKey)

}