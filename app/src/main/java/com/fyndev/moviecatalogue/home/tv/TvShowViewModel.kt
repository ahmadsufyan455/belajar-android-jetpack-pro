package com.fyndev.moviecatalogue.home.tv

import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.data.DataMovie
import com.fyndev.moviecatalogue.data.MovieEntity

class TvShowViewModel : ViewModel() {

    fun getDataTvShow(): ArrayList<MovieEntity> = DataMovie.getTvShow()

}