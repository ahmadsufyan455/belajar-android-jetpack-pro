package com.fyndev.moviecatalogue.detail

import android.os.Bundle
import android.view.*
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.fyndev.moviecatalogue.R
import com.fyndev.moviecatalogue.data.source.local.entity.MovieEntity
import com.fyndev.moviecatalogue.data.source.local.entity.TvShowEntity
import com.fyndev.moviecatalogue.databinding.FragmentDetailBinding
import com.fyndev.moviecatalogue.viewmodel.ViewModelFactory
import com.fyndev.moviecatalogue.vo.Status

class DetailFragment : Fragment() {

    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }

    private val args: DetailFragmentArgs by navArgs()
    private val binding: FragmentDetailBinding by viewBinding()
    private lateinit var title: String
    private lateinit var viewModel: DetailViewModel
    private lateinit var data: String
    private var menu: Menu? = null

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
        data = args.state

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        viewModel.setState(id, data)

        if (data == MOVIE) {
            // get detail movies
            viewModel.getDetailMovie().observe(viewLifecycleOwner, { detailMovie ->
                when (detailMovie.status) {
                    Status.LOADING -> showProgressbar(true)
                    Status.SUCCESS -> {
                        if (detailMovie != null) {
                            detailMovie.data?.let { populateMovies(it) }
                            showProgressbar(false)
                        }
                    }
                    Status.ERROR -> {
                        showProgressbar(false)
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (data == TV_SHOW) {
            // get detail tv show
            viewModel.getDetailTvShow().observe(viewLifecycleOwner, { detailTvShow ->
                when (detailTvShow.status) {
                    Status.LOADING -> showProgressbar(true)
                    Status.SUCCESS -> {
                        if (detailTvShow != null) {
                            detailTvShow.data?.let { populateTvShow(it) }
                            showProgressbar(false)
                        }
                    }
                    Status.ERROR -> {
                        showProgressbar(false)
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun populateMovies(movieEntity: MovieEntity) {
        val imgUrl = "https://image.tmdb.org/t/p/w533_and_h300_bestv2"
        binding.ivBackdrop.load(imgUrl + movieEntity.backdrop_path) {
            crossfade(true)
            crossfade(1000)
        }
        title = movieEntity.title.toString()
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
        title = tvShowEntity.name.toString()
        binding.tvTitle.text = tvShowEntity.name
        binding.tvDate.text = tvShowEntity.first_air_date
        binding.tvRating.text = tvShowEntity.vote_average
        binding.tvDescription.text = tvShowEntity.overview
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        if (data == MOVIE) {
            viewModel.getDetailMovie().observe(viewLifecycleOwner, { detailMovie ->
                when (detailMovie.status) {
                    Status.LOADING -> showProgressbar(true)
                    Status.SUCCESS -> {
                        if (detailMovie != null) {
                            showProgressbar(false)
                            val state = detailMovie.data?.isFavorite
                            state?.let { setFavoriteState(it) }
                        }
                    }
                    Status.ERROR -> {
                        showProgressbar(false)
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (data == TV_SHOW) {
            viewModel.getDetailTvShow().observe(viewLifecycleOwner, { detailTvShow ->
                when (detailTvShow.status) {
                    Status.LOADING -> showProgressbar(true)
                    Status.SUCCESS -> {
                        if (detailTvShow != null) {
                            showProgressbar(false)
                            val state = detailTvShow.data?.isFavorite
                            state?.let { setFavoriteState(it) }
                        }
                    }
                    Status.ERROR -> {
                        showProgressbar(false)
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ic_share) {
            onShareClick()
            return true
        } else if (item.itemId == R.id.ic_favorite) {
            if (data == MOVIE) {
                viewModel.setFavoriteMovie()
                return true
            } else if (data == TV_SHOW) {
                viewModel.setFavoriteTvShow()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onShareClick() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(requireActivity())
            .setType(mimeType)
            .setChooserTitle("Share this movie now.")
            .setText("Amazing movie is now playing : $title")
            .startChooser()
    }

    private fun showProgressbar(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setFavoriteState(isFavorite: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.ic_favorite)
        if (isFavorite) {
            menuItem?.icon = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_favorite)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(requireActivity(), R.drawable.ic_favorite_border)
        }
    }
}