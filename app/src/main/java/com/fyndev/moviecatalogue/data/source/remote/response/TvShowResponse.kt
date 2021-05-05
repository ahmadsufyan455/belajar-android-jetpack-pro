package com.fyndev.moviecatalogue.data.source.remote.response

import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity

data class TvShowResponse(
    val results: ArrayList<TvShowEntity>
)
