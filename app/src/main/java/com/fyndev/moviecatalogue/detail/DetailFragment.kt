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
import com.fyndev.moviecatalogue.data.MovieEntity
import com.fyndev.moviecatalogue.databinding.FragmentDetailBinding

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

        val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]
        viewModel.setSelectedById(id)
        setData(viewModel.getDetail())
    }

    private fun setData(movieEntity: MovieEntity) {
        binding.ivBackdrop.load(movieEntity.backdrop) {
            crossfade(true)
            crossfade(1000)
        }
        title = movieEntity.title
        binding.tvTitle.text = movieEntity.title
        binding.tvDate.text = movieEntity.release
        binding.tvRating.text = movieEntity.rating
        binding.tvDescription.text = movieEntity.description
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