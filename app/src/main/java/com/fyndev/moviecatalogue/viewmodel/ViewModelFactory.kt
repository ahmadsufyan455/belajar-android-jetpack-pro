package com.fyndev.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.di.Injection
import com.fyndev.moviecatalogue.home.movie.MovieViewModel
import com.fyndev.moviecatalogue.home.tv.TvShowViewModel

class ViewModelFactory private constructor(private val movieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance
                            ?: ViewModelFactory(Injection.provideRepository()).apply { instance = this }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}