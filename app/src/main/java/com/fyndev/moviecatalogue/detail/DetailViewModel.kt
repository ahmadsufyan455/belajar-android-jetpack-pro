package com.fyndev.moviecatalogue.detail

import androidx.lifecycle.ViewModel
import com.fyndev.moviecatalogue.utils.DataMovie
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity

class DetailViewModel : ViewModel() {
    private lateinit var id: String

    fun setSelectedById(id: String) {
        this.id = id
    }

    fun getDetail(): MovieEntity {
        lateinit var movie: MovieEntity
        val entityList = ArrayList<MovieEntity>()
        entityList.addAll(DataMovie.getMovie())
        entityList.addAll(DataMovie.getTvShow())
        for (movieEntity in entityList) {
            if (movieEntity.id == id) {
                movie = movieEntity
            }
        }
        return movie
    }
}