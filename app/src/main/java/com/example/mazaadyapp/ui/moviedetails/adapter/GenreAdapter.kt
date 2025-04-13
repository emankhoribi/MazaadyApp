package com.example.mazaadyapp.ui.moviedetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.details.Genre
import com.example.mazaadyapp.databinding.ItemGenreBinding

class GenreAdapter  :
    ListAdapter<Genre, GenreAdapter.ViewHolder>(GenreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val itemBinding: ItemGenreBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(genre: Genre) {
            itemBinding.genreTv.text = genre.name
        }

    }


    class GenreDiffCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(
            oldItem: Genre,
            newItem: Genre
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Genre,
            newItem: Genre
        ): Boolean {
            return oldItem == newItem
        }
    }


}