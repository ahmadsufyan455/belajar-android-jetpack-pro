package com.fyndev.moviecatalogue.data.source.local.entity

data class MovieEntity(
        var id: Int = 0,
        var title: String = "",
        var overview: String = "",
        var release_date: String = "",
        var vote_average: String = "",
        var poster_path: String = "",
        var backdrop_path: String = ""
)