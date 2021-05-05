package com.fyndev.moviecatalogue.home.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getTvShow(): LiveData<Resource<List<TvShowEntity>>> = movieRepository.getTvShow()

}