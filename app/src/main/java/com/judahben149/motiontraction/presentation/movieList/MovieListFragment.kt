package com.judahben149.motiontraction.presentation.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.FragmentMovieListBinding
import com.judahben149.motiontraction.presentation.movieList.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MovieListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private val viewModel: MovieListViewModel by viewModels()
    private val mDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MovieListAdapter(requireContext()) { id ->
            val bundle = Bundle()
            bundle.putInt("MOVIE_ID", id)
            findNavController().navigate(R.id.MovieDetailFragment, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        setupOptionsMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toggleShimmer(true)
        initViews()
        setListeners()
        collectState()
    }


    private fun initViews() {
        recyclerView = binding.rvDiscoverMovies

        layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun setListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun collectState() {
        mDisposable.add(
            viewModel.getMovieList().subscribe { pagingData ->
                if (viewModel.state.value?.filterType == MovieType.FAVORITES) {
                    adapter.submitData(lifecycle, pagingData.filter { it.isFavorite })
                } else {
                    adapter.submitData(lifecycle, pagingData)
                }
                toggleShimmer(false)
            }
        )

        viewModel.state.observe(viewLifecycleOwner) {
            if (it.movieList != null) {
//                toggleShimmer(false)
            }
        }
    }

    private fun setupOptionsMenu() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_show_favorites -> {
                    viewModel.updateFilter(MovieType.FAVORITES)
                    binding.toolbar.menu.findItem(R.id.action_show_favorites).isVisible = false
                    binding.toolbar.menu.findItem(R.id.action_show_all).isVisible = true
                    true
                }
                R.id.action_show_all -> {
                    viewModel.updateFilter(MovieType.ALL)
                    binding.toolbar.menu.findItem(R.id.action_show_all).isVisible = false
                    binding.toolbar.menu.findItem(R.id.action_show_favorites).isVisible = true
                    true
                }

                else -> false
            }
        }
    }

    private fun toggleShimmer(shouldStart: Boolean) {
        if (shouldStart) {
            binding.shimmerLayout.startShimmer()
        } else {
            binding.shimmerLayout.visibility = View.INVISIBLE
            binding.shimmerLayout.stopShimmer()
        }
    }

    override fun onPause() {
        super.onPause()
        toggleShimmer(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.dispose()
        _binding = null
    }
}