package com.fyndev.moviecatalogue.service

import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {
    /*
     * Movies
     */
    @GET("movie/now_playing")
    fun getMovie(@Query("api_key") apiKey: String): Call<MovieResponse>

    /*
    * Tv Show
    */
    @GET("tv/airing_today")
    fun getTvShow(@Query("api_key") apiKey: String): Call<TvShowResponse>
}