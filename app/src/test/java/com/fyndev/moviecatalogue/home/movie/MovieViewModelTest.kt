package com.fyndev.moviecatalogue.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.utils.DataMovie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    companion object {
        private const val apiKey = "e40c34a2a097d56ae9509a5ab8c47d44"
    }

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<MovieResponse>

    @Before
    fun setup() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = DataMovie.getMovie()
        val movie = MutableLiveData<MovieResponse>()
        movie.value = dummyMovie

        `when`(movieRepository.getMovies(apiKey)).thenReturn(movie)
        val movieEntities = viewModel.getDataMovie().value
        verify(movieRepository).getMovies(apiKey)
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.results?.size)

        viewModel.getDataMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}