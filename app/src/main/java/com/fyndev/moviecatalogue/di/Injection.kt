package com.fyndev.moviecatalogue.di

import android.content.Context
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.LocalDataSource
import com.fyndev.moviecatalogue.data.source.local.room.MovieDatabase
import com.fyndev.moviecatalogue.data.source.remote.RemoteDataSource
import com.fyndev.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}