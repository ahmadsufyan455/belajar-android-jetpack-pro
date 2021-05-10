package com.fyndev.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.fyndev.moviecatalogue.data.source.local.LocalDataSource
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.data.source.remote.RemoteDataSource
import com.fyndev.moviecatalogue.utils.AppExecutors
import com.fyndev.moviecatalogue.utils.DataMovie
import com.fyndev.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val movieResponse = DataMovie.getMovie()
    private val tvShowResponse = DataMovie.getTvShow()

    private val movieId = movieResponse[0].id
    private val tvId = tvShowResponse[0].id
    private val detailMovieEntity = DataMovie.getDetailMovie(movieId)
    private val detailTvShowEntity = DataMovie.getDetailTvShow(tvId)

    @Test
    fun getMovies() {
        val dataMovie = MutableLiveData<List<MovieEntity>>()
        dataMovie.value = DataMovie.getMovie()
        `when`(local.getMovies()).thenReturn(dataMovie)

        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getMovies())
        verify(local).getMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShow() {
        val dataMovie = MutableLiveData<List<TvShowEntity>>()
        dataMovie.value = DataMovie.getTvShow()
        `when`(local.getTvShows()).thenReturn(dataMovie)

        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getTvShow())
        verify(local).getTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dataMovie = MutableLiveData<MovieEntity>()
        dataMovie.value = detailMovieEntity
        `when`(local.getMovieById(movieId)).thenReturn(dataMovie)

        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId))
        verify(local).getMovieById(movieId)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse[0].id, movieEntities.data?.id)
    }

    @Test
    fun getDetailTvShow() {
        val dataTvShow = MutableLiveData<TvShowEntity>()
        dataTvShow.value = detailTvShowEntity
        `when`(local.getTvShowById(tvId)).thenReturn(dataTvShow)

        val tvEntities = LiveDataTestUtil.getValue(movieRepository.getDetailTvShow(tvId))
        verify(local).getTvShowById(tvId)
        assertNotNull(tvEntities.data)
        assertEquals(tvShowResponse[0].id, tvEntities.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataMovie = MutableLiveData<List<MovieEntity>>()
        dataMovie.value = DataMovie.getMovie()
        `when`(local.getFavoriteMovie()).thenReturn(dataMovie)

        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getFavoriteMovie())
        verify(local).getFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getFavoriteTvShow() {
        val dataMovie = MutableLiveData<List<TvShowEntity>>()
        dataMovie.value = DataMovie.getTvShow()
        `when`(local.getFavoriteTvShow()).thenReturn(dataMovie)

        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getFavoriteTvShow())
        verify(local).getFavoriteTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.size.toLong())
    }
}