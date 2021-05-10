package com.fyndev.moviecatalogue.home.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun setFavoriteMovie(movie: MovieEntity) {
        val newState = !movie.isFavorite
        movieRepository.setFavoriteMovie(movie, newState)
    }

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        return movieRepository.getFavoriteMovie()
    }

}