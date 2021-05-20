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
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "first_air_date")
    val first_air_date: String? = null,

    @ColumnInfo(name = "vote_average")
    val vote_average: String? = null,

    @ColumnInfo(name = "poster_path")
    val poster_path: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)