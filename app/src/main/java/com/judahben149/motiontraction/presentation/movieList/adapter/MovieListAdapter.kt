package com.judahben149.motiontraction.presentation.movieList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.judahben149.motiontraction.R
import com.judahben149.motiontraction.databinding.ItemCardMovieBinding
import com.judahben149.motiontraction.domain.models.ListMovie
import com.judahben149.motiontraction.utils.Constants.BACKDROP_BASE_URL
import com.judahben149.motiontraction.utils.parseFriendlyDate

class MovieListAdapter(
    private val context: Context,
    private val onMovieItemClicked: (id: Int) -> Unit
) : PagingDataAdapter<ListMovie, MovieListAdapter.MovieListViewHolder>(MoviesAdapterDiffer()) {

    inner class MovieListViewHolder(private val binding: ItemCardMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(listMovieItem: ListMovie) {
            binding.tvMovieName.text = listMovieItem.title
            binding.tvMovieDate.text = listMovieItem.releaseDate.parseFriendlyDate()
            binding.cardItemMovie.setOnClickListener {
                onMovieItemClicked(listMovieItem.id.toInt())
            }

            Glide.with(context)
                .load(BACKDROP_BASE_URL + listMovieItem.posterPath)
                .placeholder(R.drawable.poster_placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivMovieImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding =
            ItemCardMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }
    }


    class MoviesAdapterDiffer : DiffUtil.ItemCallback<ListMovie>() {
        override fun areItemsTheSame(
            oldItem: ListMovie,
            newItem: ListMovie
        ): Boolean {
            return oldItem.id.toInt() == newItem.id.toInt()
        }

        override fun areContentsTheSame(
            oldItem: ListMovie,
            newItem: ListMovie
        ): Boolean {
            return oldItem == newItem
        }
    }
}