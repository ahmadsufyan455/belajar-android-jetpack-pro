package com.fyndev.moviecatalogue.home.movie

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
import com.fyndev.moviecatalogue.adapter.MovieAdapter
import com.fyndev.moviecatalogue.data.MovieEntity
import com.fyndev.moviecatalogue.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private val binding: FragmentMovieBinding by viewBinding()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
        )[MovieViewModel::class.java]
        val movies = viewModel.getDataMovie()

        val movieAdapter = MovieAdapter()
        movieAdapter.setData(movies)

        // setup recyclerview
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        // set navigate to detail
        movieAdapter.setOnItemClickCallBack(object : MovieAdapter.OnItemClickCallBack {
            override fun onItemClicked(movieEntity: MovieEntity) {
                val directions =
                        MovieFragmentDirections.actionMovieFragmentToDetailFragment(movieEntity.id)
                findNavController().navigate(directions)
            }
        })
    }
}