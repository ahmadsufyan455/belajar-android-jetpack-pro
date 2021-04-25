package com.fyndev.moviecatalogue.detail

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.fyndev.moviecatalogue.R
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.databinding.FragmentDetailBinding
import com.fyndev.moviecatalogue.viewmodel.ViewModelFactory

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private val binding: FragmentDetailBinding by viewBinding()
    private lateinit var title: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.id

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        viewModel.setSelectedById(id)

        // get detail movies
        viewModel.getDetailMovie().observe(viewLifecycleOwner, { detailMovie ->
            if (detailMovie != null) {
                populateMovies(detailMovie)
            }
        })

        // get detail tv show
        viewModel.getDetailTvShow().observe(viewLifecycleOwner, { detailTvShow ->
            if (detailTvShow != null) {
                populateTvShow(detailTvShow)
            }
        })
    }

    private fun populateMovies(movieEntity: MovieEntity) {
        val imgUrl = "https://image.tmdb.org/t/p/w533_and_h300_bestv2"
        binding.ivBackdrop.load(imgUrl + movieEntity.backdrop_path) {
            crossfade(true)
            crossfade(1000)
        }
        title = movieEntity.title
        binding.tvTitle.text = movieEntity.title
        binding.tvDate.text = movieEntity.release_date
        binding.tvRating.text = movieEntity.vote_average
        binding.tvDescription.text = movieEntity.overview
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        val imgUrl = "https://image.tmdb.org/t/p/w533_and_h300_bestv2"
        binding.ivBackdrop.load(imgUrl + tvShowEntity.backdrop_path) {
            crossfade(true)
            crossfade(1000)
        }
        title = tvShowEntity.name
        binding.tvTitle.text = tvShowEntity.name
        binding.tvDate.text = tvShowEntity.first_air_date
        binding.tvRating.text = tvShowEntity.vote_average
        binding.tvDescription.text = tvShowEntity.overview
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_share) {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Amazing movie is now playing : $title")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to:"))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}