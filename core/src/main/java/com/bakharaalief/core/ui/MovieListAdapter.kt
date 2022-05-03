package com.bakharaalief.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.core.R
import com.bakharaalief.core.databinding.ItemMovieBinding
import com.bakharaalief.core.domain.model.Movie
import com.bumptech.glide.Glide

class MovieListAdapter(val onItemClick: (Movie) -> Unit) :
    ListAdapter<Movie, MovieListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val cardItem = binding.movieCardItem

        fun bind(movie: Movie) {
            displayImage(movie.posterPath)
            binding.movieTitleItem.text = movie.originalTitle
        }

        private fun displayImage(url: String) {
            Glide
                .with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/$url")
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(binding.movieCoverItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.cardItem.setOnClickListener { onItemClick(data) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }
}