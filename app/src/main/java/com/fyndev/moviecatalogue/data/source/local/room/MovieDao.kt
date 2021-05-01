package com.fyndev.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity

interface MovieDao {

    @Query("SELECT * FROM movie_entities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tv_entities")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Transaction
    @Query("SELECT * FROM tv_entities WHERE id = :id")
    fun getTvShowById(id: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

}