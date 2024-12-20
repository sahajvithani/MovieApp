package com.catalystmi.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.catalystmi.movieapp.R
import com.catalystmi.movieapp.data.model.Cast
import com.catalystmi.movieapp.databinding.ItemCastBinding
import com.catalystmi.movieapp.util.Constants
import com.makeramen.roundedimageview.RoundedImageView

class CastAdapter(private val movieList: List<Cast>) :
    RecyclerView.Adapter<CastAdapter.MovieCastViewHolder>() {

    inner class MovieCastViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: Cast) {
            // For image loading, you can use Glide, Picasso, etc.
            Glide.with(itemView.context)
                .load(Constants.IMAGE_URL + cast.profilePath)
                .placeholder(R.drawable.avatar_placeholder)
                .into(binding.civAvatar)
            binding.tvCastName.text = cast.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCastViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}