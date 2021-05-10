package com.fyndev.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fyndev.moviecatalogue.data.source.local.LocalDataSource
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.data.source.remote.ApiResponse
import com.fyndev.moviecatalogue.data.source.remote.RemoteDataSource
import com.fyndev.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.fyndev.moviecatalogue.data.source.remote.response.MovieResponse
import com.fyndev.moviecatalogue.data.source.remote.response.TvShowResponse
import com.fyndev.moviecatalogue.utils.AppExecutors
import com.fyndev.moviecatalogue.vo.Resource

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getMovies()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: MovieResponse) {
                localDataSource.insertMovies(data.results)
            }
        }.asLiveData()
    }

    override fun getTvShow(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localDataSource.getTvShows()
            }

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> {
                return remoteDataSource.getTvShow()
            }

            override fun saveCallResult(data: TvShowResponse) {
                localDataSource.insertTvShows(data.results)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieById(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> {
                return remoteDataSource.getDetailMovie(id)
            }

            override fun saveCallResult(data: DetailMovieResponse) {
                val movie = MovieEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.release_date,
                    data.vote_average,
                    data.poster_path,
                    data.backdrop_path
                )
                localDataSource.updateMovie(movie)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getTvShowById(id)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> {
                return remoteDataSource.getDetailTvShow(id)
            }

            override fun saveCallResult(data: DetailTvShowResponse) {
                val tvShow = TvShowEntity(
                    data.id,
                    data.name,
                    data.overview,
                    data.first_air_date,
                    data.vote_average,
                    data.poster_path,
                    data.backdrop_path
                )
                localDataSource.updateTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setMovieStatus(movie, isFavorite) }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setTvShowStatus(tvShow, isFavorite) }
    }
}