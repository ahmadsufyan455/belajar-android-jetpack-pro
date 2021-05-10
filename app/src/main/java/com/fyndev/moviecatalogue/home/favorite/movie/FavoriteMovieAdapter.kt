package com.fyndev.moviecatalogue.home.favorite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.databinding.RvItemBinding

class FavoriteMovieAdapter :
    PagedListAdapter<MovieEntity, FavoriteMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    fun getSwipeData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    inner class ViewHolder(private val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
        fun bind(movieEntity: MovieEntity) {
            binding.ivPoster.load(imageUrl + movieEntity.poster_path) {
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(10f))
            }
            binding.tvTitle.text = movieEntity.title
            binding.tvDescription.text = movieEntity.overview
            itemView.setOnClickListener { onItemClickCallBack.onItemClicked(movieEntity) }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(movieEntity: MovieEntity)
    }
}