package com.fyndev.moviecatalogue.data.source.remote.response

import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity

data class MovieResponse(
    val results: ArrayList<MovieEntity>
)
