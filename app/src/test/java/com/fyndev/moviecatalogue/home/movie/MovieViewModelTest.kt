package com.fyndev.moviecatalogue.home.movie

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getDataMovie() {
        val movieEntities = viewModel.getDataMovie()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }
}