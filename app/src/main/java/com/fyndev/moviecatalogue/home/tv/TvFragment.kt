package com.fyndev.moviecatalogue.home.tv

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
import com.fyndev.moviecatalogue.databinding.FragmentTvBinding

class TvFragment : Fragment() {

    private val binding: FragmentTvBinding by viewBinding()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
        val tvShowAdapter = MovieAdapter()
        tvShowAdapter.setData(viewModel.getDataTvShow())

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        tvShowAdapter.setOnItemClickCallBack(object : MovieAdapter.OnItemClickCallBack {
            override fun onItemClicked(movieEntity: MovieEntity) {
                val direction = TvFragmentDirections.actionTvFragmentToDetailFragment(movieEntity.id)
                findNavController().navigate(direction)
            }
        })
    }
}