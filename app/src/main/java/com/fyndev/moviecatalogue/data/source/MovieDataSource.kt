package com.fyndev.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse

interface MovieDataSource {

    fun getMovies(apiKey: String): LiveData<MovieResponse>

    fun getTvShow(apiKey: String): LiveData<TvShowResponse>

    fun getDetailMovie(id: Int, apiKey: String): LiveData<MovieEntity>

    fun getDetailTvShow(id: Int, apiKey: String): LiveData<TvShowEntity>

}