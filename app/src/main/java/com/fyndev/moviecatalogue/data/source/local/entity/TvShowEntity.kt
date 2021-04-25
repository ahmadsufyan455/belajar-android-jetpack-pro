package com.fyndev.moviecatalogue.data.source.local.entity

data class TvShowEntity(
        var id: Int = 0,
        var name: String = "",
        var overview: String = "",
        var first_air_date: String = "",
        var vote_average: String = "",
        var poster_path: String = "",
        var backdrop_path: String = ""
)