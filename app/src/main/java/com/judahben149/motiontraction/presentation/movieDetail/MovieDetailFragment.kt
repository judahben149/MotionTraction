package com.judahben149.motiontraction.presentation.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.judahben149.motiontraction.databinding.FragmentMovieDetailBinding
import com.judahben149.motiontraction.presentation.movieDetail.epoxy.MovieDetailEpoxyController
import com.judahben149.motiontraction.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private var movieId: Int = 0

    private val viewModel: MovieDetailViewModel by viewModels()
    private val navController by lazy { findNavController() }

    private val controller by lazy {
        MovieDetailEpoxyController(
            context = requireContext(),
            onMovieLiked = { handleLikeAction() },
            onLoadedItemsComplete = { toggleShimmer(false) })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener { navController.popBackStack() }
        binding.epoxyRvMovieDetail.setController(controller)

        setupListeners()
        collectState()
        getMovieDetail()
    }

    private fun setupListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            getMovieDetail()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun collectState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            if (state.isGetMovieDetailSuccessful && state.isGetMovieCastSuccessful) {
                controller.setData(state)
            }

            if (state.isError) {
                handleError(state.errorMessage)
            }
        }
    }

    private fun getMovieDetail() {
        val id = arguments?.getInt("MOVIE_ID")
        id?.let { movieId = it }

        viewModel.getMovie(movieId)
    }

    private fun handleLikeAction() {
        viewModel.addToFavorites()
    }

    private fun handleError(erroMessage: String) {
        erroMessage.showSnackBar(binding.root)
        viewModel.notifyErrorHandled()
    }

    private fun toggleShimmer(shouldStart: Boolean) {
        if (shouldStart) {
            binding.shimmerLayoutDetail.shimmer.visibility = View.VISIBLE
            binding.shimmerLayoutDetail.shimmer.startShimmer()
        } else {
            binding.shimmerLayoutDetail.shimmer.visibility = View.INVISIBLE
            binding.shimmerLayoutDetail.shimmer.stopShimmer()
        }
    }

    override fun onResume() {
        super.onResume()
        toggleShimmer(true)
    }

    override fun onPause() {
        super.onPause()
        toggleShimmer(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}