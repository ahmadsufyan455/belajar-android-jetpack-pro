package com.fyndev.moviecatalogue.home.movie

import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.data.DataMovie
import com.fyndev.moviecatalogue.data.MovieEntity

class MovieViewModel : ViewModel() {

    fun getDataMovie(): ArrayList<MovieEntity> = DataMovie.getMovie()

}