package com.fyndev.moviecatalogue.data

data class MovieEntity(
        var id: String = "",
        var title: String = "",
        var description: String = "",
        var release: String = "",
        var rating: String = "",
        var poster: String = "",
        var backdrop: String = ""
)