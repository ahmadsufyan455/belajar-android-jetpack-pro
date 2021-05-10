package com.fyndev.moviecatalogue.home.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        val newState = !tvShow.isFavorite
        movieRepository.setFavoriteTvShow(tvShow, newState)
    }

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        return movieRepository.getFavoriteTvShow()
    }

}