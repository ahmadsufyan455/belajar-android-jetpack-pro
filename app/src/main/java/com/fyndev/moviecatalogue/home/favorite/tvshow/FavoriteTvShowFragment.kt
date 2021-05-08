package com.fyndev.moviecatalogue.home.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fyndev.moviecatalogue.R
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.fyndev.moviecatalogue.detail.DetailFragment
import com.fyndev.moviecatalogue.home.favorite.FavoriteFragmentDirections
import com.fyndev.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {

    private val binding: FragmentFavoriteTvShowBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]
        val favoriteTvShowAdapter = FavoriteTvShowAdapter()

        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { favTvShow ->
            if (favTvShow != null) {
                favoriteTvShowAdapter.setData(favTvShow)
            }
        })

        // setup recyclerview
        with(binding.rvFavoriteTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteTvShowAdapter
        }

        favoriteTvShowAdapter.setOnItemClickCallBack(object :
            FavoriteTvShowAdapter.OnItemClickCallBack {
            override fun onItemClicked(tvShowEntity: TvShowEntity) {
                val direction =
                    FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment2(
                        tvShowEntity.id,
                        DetailFragment.TV_SHOW
                    )
                findNavController().navigate(direction)
            }
        })
    }
}