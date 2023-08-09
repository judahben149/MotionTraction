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
        initViews()
        setListeners()
        collectMoviePagedList()
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

    private fun collectMoviePagedList() {
        mDisposable.add(
            viewModel.getMovieList().subscribe { pagingData ->
                if (viewModel.state.value?.filterType == MovieType.FAVORITES) {
                    adapter.submitData(lifecycle, pagingData.filter { it.isFavorite })
                    binding.toolbar.menu.findItem(R.id.action_filter_favorites).title = "Show all"
                } else {
                    adapter.submitData(lifecycle, pagingData)
                    binding.toolbar.menu.findItem(R.id.action_filter_favorites).title = "Filter favorites"
                }
            }
        )
    }

    private fun setupOptionsMenu() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_filter_favorites -> {
                    if (viewModel.state.value?.filterType == MovieType.ALL) {
                        viewModel.updateFilter(MovieType.FAVORITES)
                    } else {
                        viewModel.updateFilter(MovieType.ALL)
                    }

                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.dispose()
        _binding = null
    }
}