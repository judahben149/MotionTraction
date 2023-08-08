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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private var movieId: Int = 0

    private val viewModel: MovieDetailViewModel by viewModels()
    private val controller by lazy { MovieDetailEpoxyController(requireContext()) }
    private val navController by lazy { findNavController() }

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

        val id = arguments?.getInt("MOVIE_ID")
        id?.let { movieId = it }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            if (state.isGetMovieCastSuccessful && state.isGetMovieDetailSuccessful) {
                controller.setData(state)
            }
        }

        viewModel.getMovieDetail(movieId)
        viewModel.getMovieCredits(movieId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}