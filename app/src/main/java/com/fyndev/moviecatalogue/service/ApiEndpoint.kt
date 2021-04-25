package com.fyndev.moviecatalogue.service

import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {
    /*
     * Movies
     */
    @GET("movie/now_playing")
    fun getMovie(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
            @Path("movie_id") movieId: Int,
            @Query("api_key") apiKey: String): Call<MovieEntity>

    /*
    * Tv Show
    */
    @GET("tv/airing_today")
    fun getTvShow(@Query("api_key") apiKey: String): Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
            @Path("tv_id") tvId: Int,
            @Query("api_key") apiKey: String): Call<TvShowEntity>
}