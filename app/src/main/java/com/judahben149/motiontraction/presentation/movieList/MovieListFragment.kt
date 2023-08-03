package com.judahben149.motiontraction.presentation.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.judahben149.motiontraction.BuildConfig
import com.judahben149.motiontraction.databinding.FragmentMovieListBinding


class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Snackbar.make(binding.root, BuildConfig.API_KEY, Snackbar.LENGTH_INDEFINITE).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}