package com.fyndev.moviecatalogue.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getDataMovie(): LiveData<Resource<List<MovieEntity>>> = movieRepository.getMovies()

}