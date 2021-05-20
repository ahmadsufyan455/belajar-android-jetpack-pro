package com.fyndev.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "release_date")
    val release_date: String? = null,

    @ColumnInfo(name = "vote_average")
    val vote_average: String? = null,

    @ColumnInfo(name = "poster_path")
    val poster_path: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)