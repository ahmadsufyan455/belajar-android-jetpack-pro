package com.fyndev.moviecatalogue.home.favorite.movie

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
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.fyndev.moviecatalogue.detail.DetailFragment
import com.fyndev.moviecatalogue.home.favorite.FavoriteFragmentDirections
import com.fyndev.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private val binding: FragmentFavoriteMovieBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
        val favoriteMovieAdapter = FavoriteMovieAdapter()

        viewModel.getFavoriteMovie().observe(viewLifecycleOwner, { favMovie ->
            if (favMovie != null) {
                favoriteMovieAdapter.submitList(favMovie)
            }
        })

        // setup recyclerview
        with(binding.rvFavoriteMovie) {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteMovieAdapter
        }

        // set navigate to detail
        favoriteMovieAdapter.setOnItemClickCallBack(object :
            FavoriteMovieAdapter.OnItemClickCallBack {
            override fun onItemClicked(movieEntity: MovieEntity) {
                val directions =
                    FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment2(
                        movieEntity.id,
                        DetailFragment.MOVIE
                    )
                findNavController().navigate(directions)
            }
        })
    }
}