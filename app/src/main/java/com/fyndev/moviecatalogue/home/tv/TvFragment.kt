package com.fyndev.moviecatalogue.home.tv

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
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.databinding.FragmentTvBinding
import com.fyndev.moviecatalogue.detail.DetailFragment
import com.fyndev.moviecatalogue.viewmodel.ViewModelFactory
import com.fyndev.moviecatalogue.vo.Status

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

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        val tvShowAdapter = TvShowAdapter()

        viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        tvShow.data?.let { tvShowAdapter.setData(it) }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        tvShowAdapter.setOnItemClickCallBack(object : TvShowAdapter.OnItemClickCallBack {
            override fun onItemClicked(tvShowEntity: TvShowEntity) {
                val direction = TvFragmentDirections.actionTvFragmentToDetailFragment(
                    tvShowEntity.id,
                    DetailFragment.TV_SHOW
                )
                findNavController().navigate(direction)
            }
        })
    }
}