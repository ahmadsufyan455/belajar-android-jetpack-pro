package com.fyndev.moviecatalogue.di

import android.content.Context
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.remote.RemoteDataSource
import com.fyndev.moviecatalogue.service.ApiClient
import com.fyndev.moviecatalogue.service.ApiEndpoint

object Injection {
    fun provideRepository(): MovieRepository {
        val apiEndpoint: ApiEndpoint = ApiClient.instance
        val remoteDataSource = RemoteDataSource.getInstance(apiEndpoint)
        return MovieRepository.getInstance(remoteDataSource)
    }
}