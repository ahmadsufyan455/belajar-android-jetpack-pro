package com.fyndev.moviecatalogue.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fyndev.moviecatalogue.BuildConfig
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.utils.DataMovie
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    companion object {
        private const val apiKey = BuildConfig.MOVIE_KEY
    }

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataMovie.getMovie().results[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var detailMovieObserver: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
        viewModel.setSelectedById(movieId)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(movieRepository.getDetailMovie(movieId, apiKey)).thenReturn(movie)
        val movieEntity = viewModel.getDetailMovie().value as MovieEntity
        verify(movieRepository).getDetailMovie(movieId, apiKey)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.poster_path, movieEntity.poster_path)
        assertEquals(dummyMovie.backdrop_path, movieEntity.backdrop_path)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.release_date, movieEntity.release_date)
        assertEquals(dummyMovie.vote_average, movieEntity.vote_average)
        assertEquals(dummyMovie.overview, movieEntity.overview)

        viewModel.getDetailMovie().observeForever(detailMovieObserver)
        verify(detailMovieObserver).onChanged(dummyMovie)
    }
}