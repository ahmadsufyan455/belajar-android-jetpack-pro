package com.fyndev.moviecatalogue.service

import com.fyndev.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.DetailTvShowResponse
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
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    /*
    * Tv Show
    */
    @GET("tv/on_the_air")
    fun getTvShow(@Query("api_key") apiKey: String): Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailTvShowResponse>
}