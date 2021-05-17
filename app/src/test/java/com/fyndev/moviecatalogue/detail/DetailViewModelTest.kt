package com.fyndev.moviecatalogue.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.detail.DetailViewModel.Companion.MOVIE
import com.fyndev.moviecatalogue.utils.DataMovie
import com.fyndev.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = Resource.success(DataMovie.getMovie()[0])
    private val movieId = dummyMovie.data?.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var detailMovieObserver: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
        movieId?.let { viewModel.setSelectedById(it) }
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie
        `when`(movieId?.let { movieRepository.getDetailMovie(it) }).thenReturn(movie)

        viewModel.getDetailMovieForTest().observeForever(detailMovieObserver)
        verify(detailMovieObserver).onChanged(dummyMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val detailMovie = Resource.success(DataMovie.getDetailMovie(movieId!!))
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = detailMovie

        `when`(movieRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.setState(movieId, MOVIE)
        viewModel.setFavoriteMovie()
        verify(movieRepository).setFavoriteMovie(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(detailMovieObserver)
    }
}