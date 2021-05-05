package com.fyndev.moviecatalogue.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fyndev.moviecatalogue.R
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.databinding.FragmentMovieBinding
import com.fyndev.moviecatalogue.viewmodel.ViewModelFactory
import com.fyndev.moviecatalogue.vo.Status

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

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val movieAdapter = MovieAdapter()

        viewModel.getDataMovie().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        movies.data?.let { movieAdapter.setData(it) }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })


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
                    MovieFragmentDirections.actionMovieFragmentToDetailFragment(
                        movieEntity.id,
                        "movie"
                    )
                findNavController().navigate(directions)
            }
        })
    }
}