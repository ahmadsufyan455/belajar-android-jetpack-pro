package com.fyndev.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.vo.Resource

interface MovieDataSource {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>>

    fun getTvShow(): LiveData<Resource<List<TvShowEntity>>>

    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>>

    fun getDetailTvShow(id: Int): LiveData<Resource<TvShowEntity>>

}