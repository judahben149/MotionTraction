package com.judahben149.motiontraction.presentation.movieDetail.epoxy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.judahben149.motiontraction.databinding.ItemCardCastBinding
import com.judahben149.motiontraction.domain.models.credits.Cast
import com.judahben149.motiontraction.utils.loadImage

class MovieCastAdapter(private val context: Context): ListAdapter<Cast, MovieCastAdapter.MovieCastViewHolder>(MovieCastDiffer()) {

    inner class MovieCastViewHolder(private val binding: ItemCardCastBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindItem(cast: Cast) {
            binding.tvCastName.text = cast.name
            binding.ivCast.loadImage(context, cast.profilePath) { }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val binding = ItemCardCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    class MovieCastDiffer : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }
}