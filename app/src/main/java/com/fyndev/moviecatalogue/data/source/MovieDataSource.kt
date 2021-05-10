package com.fyndev.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.vo.Resource

interface MovieDataSource {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getTvShow(): LiveData<Resource<List<TvShowEntity>>>

    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>>

    fun getDetailTvShow(id: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean)

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean)
}