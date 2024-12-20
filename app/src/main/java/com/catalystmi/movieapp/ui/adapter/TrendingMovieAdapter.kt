package com.catalystmi.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.catalystmi.movieapp.R
import com.catalystmi.movieapp.data.model.Results
import com.catalystmi.movieapp.util.Constants

class TrendingMovieAdapter(private val movieList: List<Results>, private val onClick: (Results) -> Unit) :
    RecyclerView.Adapter<TrendingMovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moviePoster: ImageView = itemView.findViewById(R.id.trending)

        fun bind(movie: Results) {
            // For image loading, you can use Glide, Picasso, etc.
            Glide.with(itemView.context)
                .load(Constants.IMAGE_URL+movie.posterPath)
                .placeholder(R.drawable.image_placeholder)
                .into(moviePoster)

            itemView.setOnClickListener {
                onClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.trending_movie_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return if (movieList.size > 5) 5 else movieList.size
    }
}