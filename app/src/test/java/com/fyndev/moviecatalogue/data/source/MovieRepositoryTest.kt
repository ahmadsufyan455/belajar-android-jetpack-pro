package com.fyndev.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fyndev.moviecatalogue.data.source.remote.RemoteDataSource
import com.fyndev.moviecatalogue.utils.DataMovie
import com.fyndev.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    companion object {
        private const val apiKey = "e40c34a2a097d56ae9509a5ab8c47d44"
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val movieResponse = DataMovie.getMovie()
    private val tvShowResponse = DataMovie.getTvShow()

    private val movieId = movieResponse.results[0].id
    private val tvId = tvShowResponse.results[0].id
    private val detailMovieEntity = DataMovie.getDetailMovie(movieId)
    private val detailTvShowEntity = DataMovie.getDetailTvShow(tvId)

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieCallback)
                .onAllMovieReceived(movieResponse)
            null
        }.`when`(remote).getMovies(eq(apiKey), any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getMovies(apiKey))
        verify(remote).getMovies(eq(apiKey), any())
        assertNotNull(movieEntities.results)
        assertEquals(movieResponse.results.size.toLong(), movieEntities.results.size.toLong())
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponse)
            null
        }.`when`(remote).getTvShow(eq(apiKey), any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getTvShow(apiKey))
        verify(remote).getTvShow(eq(apiKey), any())
        assertNotNull(tvShowEntities.results)
        assertEquals(tvShowResponse.results.size.toLong(), tvShowEntities.results.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadDetailMovieCallback)
                .onDetailReceived(detailMovieEntity)
            null
        }.`when`(remote).getDetailMovie(eq(movieId), eq(apiKey), any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId, apiKey))
        verify(remote).getDetailMovie(eq(movieId), eq(apiKey), any())
        assertNotNull(movieEntities)
        assertEquals(movieResponse.results[0].title, movieEntities.title)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadDetailTvShowCallback)
                .onDetailReceived(detailTvShowEntity)
            null
        }.`when`(remote).getDetailTvShow(eq(tvId), eq(apiKey), any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getDetailTvShow(tvId, apiKey))
        verify(remote).getDetailTvShow(eq(tvId), eq(apiKey), any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.results[0].name, tvShowEntities.name)
    }
}