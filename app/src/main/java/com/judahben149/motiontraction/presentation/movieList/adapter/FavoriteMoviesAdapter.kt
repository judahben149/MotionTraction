package com.judahben149.motiontraction.presentation.movieList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.judahben149.motiontraction.databinding.ItemCardMovieBinding
import com.judahben149.motiontraction.domain.models.favoriteMovies.FavoriteMovie
import com.judahben149.motiontraction.utils.Constants.BACKDROP_BASE_URL
import com.judahben149.motiontraction.utils.loadImage
import com.judahben149.motiontraction.utils.parseFriendlyDate

class FavoriteMoviesAdapter(
    private val context: Context,
    private val onMovieItemClicked: (id: Int) -> Unit
) : ListAdapter<FavoriteMovie, FavoriteMoviesAdapter.FavoriteMoviesViewHolder>(FavoriteMoviesAdapterDiffer()) {

    inner class FavoriteMoviesViewHolder(private val binding: ItemCardMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(favoriteMovieItem: FavoriteMovie) {
            binding.tvMovieName.text = favoriteMovieItem.title
            binding.tvMovieDate.text = favoriteMovieItem.releaseDate.parseFriendlyDate()
            binding.cardItemMovie.setOnClickListener {
                onMovieItemClicked(favoriteMovieItem.movieId)
            }

            binding.ivMovieImage.loadImage(context, BACKDROP_BASE_URL + favoriteMovieItem.posterPath) { }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        val binding =
            ItemCardMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }
    }


    class FavoriteMoviesAdapterDiffer : DiffUtil.ItemCallback<FavoriteMovie>() {
        override fun areItemsTheSame(
            oldItem: FavoriteMovie,
            newItem: FavoriteMovie
        ): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(
            oldItem: FavoriteMovie,
            newItem: FavoriteMovie
        ): Boolean {
            return oldItem == newItem
        }
    }
}