package com.fyndev.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fyndev.moviecatalogue.data.MovieEntity
import com.fyndev.moviecatalogue.databinding.RvItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    private val listMovie = ArrayList<MovieEntity>()

    fun setData(list: ArrayList<MovieEntity>) {
        listMovie.clear()
        listMovie.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovie[position])
        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listMovie[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listMovie.size

    class ViewHolder(private val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            binding.ivPoster.load(movieEntity.poster) {
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(10f))
            }
            binding.tvTitle.text = movieEntity.title
            binding.tvDescription.text = movieEntity.description
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(movieEntity: MovieEntity)
    }
}