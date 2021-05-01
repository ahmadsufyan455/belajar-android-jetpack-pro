package com.fyndev.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_entities")
data class TvShowEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "name")
        var name: String = "",

        @ColumnInfo(name = "overview")
        var overview: String = "",

        @ColumnInfo(name = "first_air_date")
        var first_air_date: String = "",

        @ColumnInfo(name = "vote_average")
        var vote_average: String = "",

        @ColumnInfo(name = "poster_path")
        var poster_path: String = "",

        @ColumnInfo(name = "backdrop_path")
        var backdrop_path: String = ""
)