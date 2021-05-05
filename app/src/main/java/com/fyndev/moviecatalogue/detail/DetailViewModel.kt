package com.fyndev.moviecatalogue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }

    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>
    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    private var id: Int = 0

    fun setSelectedById(id: Int) {
        this.id = id
    }

    fun setState(id: Int, data: String) {
        when (data) {
            MOVIE -> detailMovie = movieRepository.getDetailMovie(id)
            TV_SHOW -> detailTvShow = movieRepository.getDetailTvShow(id)
        }
    }

    fun getDetailMovie() = detailMovie

    fun getDetailTvShow() = detailTvShow
}