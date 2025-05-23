package com.example.mazaadyapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.movies.Result
import com.example.mazaadyapp.databinding.FragmentMoviesListBinding
import com.example.mazaadyapp.ui.movies.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesListFragment : Fragment(), MovieListAdapter.RecyclerViewEvent {

    private val viewModel: MoviesListViewModel by viewModels()
    private lateinit var binding: FragmentMoviesListBinding
    private var mRootView: ViewGroup? = null
    private val movieListAdapter = MovieListAdapter(this, MovieListAdapter.ViewType.TYPE_LIST)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMovieList()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

            binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.moviesRv.adapter = movieListAdapter


        viewModel.getFavorites()
        lifecycleScope.launch {
            viewModel.moviesLocalFlow.collect {
                movieListAdapter.setFavorites(it)

            }
        }

        lifecycleScope.launch {
            movieListAdapter.loadStateFlow.distinctUntilChangedBy {
                it.refresh
            }.collect{
                if( it.refresh is LoadState.Loading){
                    binding.progressBar.visibility = View.VISIBLE
                }else{
                    binding.progressBar.visibility = View.GONE
                    if (it.refresh is LoadState.Error){
                        Toast.makeText(requireContext(), (it.refresh as LoadState.Error).error.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        binding.listRg.setOnCheckedChangeListener { radioGroup, i ->
            val radioButton: View = radioGroup.findViewById(i)
            val index = radioGroup.indexOfChild(radioButton)

            var scrollPosition = 0


            // If a layout manager has already been set, get current scroll position.
            if (binding.moviesRv.layoutManager != null) {
                scrollPosition = (binding.moviesRv.layoutManager as LinearLayoutManager)
                    .findFirstCompletelyVisibleItemPosition()
            }
            when (index) {
                0 -> {

                    binding.moviesRv.layoutManager = LinearLayoutManager(requireContext())
                    binding.moviesRv.scrollToPosition(scrollPosition)
                    movieListAdapter.setItemType(MovieListAdapter.ViewType.TYPE_LIST)
                }

                1 -> {

                    binding.moviesRv.layoutManager = GridLayoutManager(
                        requireContext(), 2
                    )
                    binding.moviesRv.scrollToPosition(scrollPosition)
                    movieListAdapter.setItemType(MovieListAdapter.ViewType.TYPE_GRID)
                }
            }

        }
        binding.listRg.check(binding.listRd.id)

    }


    private fun getMovieList() {
        lifecycleScope.launch {
            viewModel.getMovieList().collectLatest {
                movieListAdapter.submitData(it)
            }

        }

    }

    override fun onItemClick(movie: Result) {

        val directions =
            MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(
                movie.id
            )
        findNavController().navigate(directions)
    }

    override fun onItemChecked(movie: Result, isChecked: Boolean) {
        lifecycleScope.launch {
            if (isChecked) {
                launch {
                    viewModel.upsertFavorite(movie)
                }
            } else {
                launch {
                    viewModel.deleteRecord(movie)
                }
            }

        }

    }

}