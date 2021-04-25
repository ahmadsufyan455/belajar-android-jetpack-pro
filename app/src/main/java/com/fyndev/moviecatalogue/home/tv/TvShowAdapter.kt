package com.fyndev.moviecatalogue.home.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.databinding.RvItemBinding

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    private val listTvShow = ArrayList<TvShowEntity>()

    fun setData(list: List<TvShowEntity>) {
        listTvShow.clear()
        listTvShow.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTvShow[position])
        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(listTvShow[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listTvShow.size

    class ViewHolder(private val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
        fun bind(tvShowEntity: TvShowEntity) {
            binding.ivPoster.load(imageUrl + tvShowEntity.poster_path) {
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(10f))
            }
            binding.tvTitle.text = tvShowEntity.name
            binding.tvDescription.text = tvShowEntity.overview
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(tvShowEntity: TvShowEntity)
    }
}