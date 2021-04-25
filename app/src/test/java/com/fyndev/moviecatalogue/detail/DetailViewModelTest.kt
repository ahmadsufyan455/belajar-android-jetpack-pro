package com.fyndev.moviecatalogue.detail

import com.fyndev.moviecatalogue.utils.DataMovie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dataMovie = DataMovie.getMovie()[0]
    private val movieId = dataMovie.id

    private val dataTvShow = DataMovie.getTvShow()[0]
    private val tvId = dataTvShow.id

    @Before
    fun setupMovie() {
        viewModel = DetailViewModel()
        viewModel.setSelectedById(movieId)
    }

    @Test
    fun getDetailMovie() {
        viewModel.setSelectedById(dataMovie.id)
        val movieEntity = viewModel.getDetail()
        assertNotNull(movieEntity)
        assertEquals(dataMovie.id, movieEntity.id)
        assertEquals(dataMovie.poster, movieEntity.poster)
        assertEquals(dataMovie.backdrop, movieEntity.backdrop)
        assertEquals(dataMovie.title, movieEntity.title)
        assertEquals(dataMovie.release, movieEntity.release)
        assertEquals(dataMovie.rating, movieEntity.rating)
        assertEquals(dataMovie.description, movieEntity.description)
    }

    @Before
    fun setupTvShow() {
        viewModel = DetailViewModel()
        viewModel.setSelectedById(tvId)
    }

    @Test
    fun getDetailTvShow() {
        viewModel.setSelectedById(dataTvShow.id)
        val tvShowEntity = viewModel.getDetail()
        assertNotNull(tvShowEntity)
        assertEquals(dataTvShow.id, tvShowEntity.id)
        assertEquals(dataTvShow.poster, tvShowEntity.poster)
        assertEquals(dataTvShow.backdrop, tvShowEntity.backdrop)
        assertEquals(dataTvShow.title, tvShowEntity.title)
        assertEquals(dataTvShow.release, tvShowEntity.release)
        assertEquals(dataTvShow.rating, tvShowEntity.rating)
        assertEquals(dataTvShow.description, tvShowEntity.description)
    }

}