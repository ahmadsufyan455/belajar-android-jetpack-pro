package com.fyndev.moviecatalogue.home.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.fyndev.moviecatalogue.R
import com.fyndev.moviecatalogue.databinding.FragmentPersonBinding

class PersonFragment : Fragment() {

    private val binding: FragmentPersonBinding by viewBinding()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivPhoto.load(R.drawable.fyn) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }
}