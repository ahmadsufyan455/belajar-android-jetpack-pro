package com.fyndev.moviecatalogue.home.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        return movieRepository.getFavoriteMovie()
    }

}