package com.fyndev.moviecatalogue.home.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fyndev.moviecatalogue.BuildConfig
import com.fyndev.moviecatalogue.data.source.MovieRepository
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse
import com.fyndev.moviecatalogue.utils.DataMovie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    companion object {
        private const val apiKey = BuildConfig.MOVIE_KEY
    }

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<TvShowResponse>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvSHow = DataMovie.getTvShow()
        val tvShow = MutableLiveData<TvShowResponse>()
        tvShow.value = dummyTvSHow

        Mockito.`when`(movieRepository.getTvShow(apiKey)).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShow().value
        Mockito.verify(movieRepository).getTvShow(apiKey)
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.results?.size)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvSHow)
    }
}