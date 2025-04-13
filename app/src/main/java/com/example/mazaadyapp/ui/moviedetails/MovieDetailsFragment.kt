package com.example.mazaadyapp.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mazaadyapp.R
import com.example.mazaadyapp.databinding.FragmentMovieDetailsBinding
import com.example.mazaadyapp.ui.moviedetails.adapter.GenreAdapter
import com.example.mazaadyapp.ui.utils.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding
    private val genreAdapter = GenreAdapter()
    val movieData: MovieDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovieDetails(movieData.id)
        binding.genreRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.genreRv.adapter = genreAdapter

        lifecycleScope.launch {
            viewModel.movieFLow.collect {
                Picasso.get().load(Constants.IMAGE_BASE_ORIGINAL.plus(it?.poster_path))
                    .into(binding.movieIv)
                binding.titleTv.text = it?.title
                binding.overviewTv.text = it?.overview
                genreAdapter.submitList(it?.genres)
            }

        }

        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()


                }
            }
        )

    }


}