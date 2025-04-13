package com.example.mazaadyapp.ui.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.domain.entity.data.Favorite
import com.example.domain.entity.movies.Result
import com.example.mazaadyapp.databinding.ItemMovieBinding
import com.example.mazaadyapp.databinding.ItemMovieGridBinding
import com.example.mazaadyapp.ui.utils.Constants
import com.squareup.picasso.Picasso

class MovieListAdapter(
    private val listener: RecyclerViewEvent,
    private var currentViewType: ViewType
) :
    PagingDataAdapter<Result, MovieListAdapter.ViewHolder>(Diffcallback()) {

    private lateinit var itemBinding: ViewBinding
    private var favoriteList = mutableListOf<Favorite>()

    public enum class ViewType {
        TYPE_GRID, TYPE_LIST
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {

        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        itemBinding = if (viewType == ViewType.TYPE_LIST.ordinal) {
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            ItemMovieGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }

        return ViewHolder(itemBinding)
    }

    fun setItemType(type: ViewType) {
        this.currentViewType = type
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return currentViewType.ordinal
    }

    fun setFavorites(moviesLocalFlow: MutableList<Favorite>?) {
        if (moviesLocalFlow != null) {
            favoriteList = moviesLocalFlow
            notifyItemRangeChanged(0, itemCount)
        }
    }

    inner class ViewHolder(private val itemBinding: ViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        fun bind(movie: Result) {
            when (currentViewType) {
                ViewType.TYPE_LIST -> listBind(movie)
                ViewType.TYPE_GRID -> gridBind(movie)
            }
        }

        private fun listBind(movie: Result) {
            var isExist = false
            val listBinding = itemBinding as ItemMovieBinding
            listBinding.titleTv.text = movie.title
            Picasso.get().load(Constants.IMAGE_BASE.plus(movie.poster_path))
                .into(listBinding.movieIv)
            listBinding.releaseDateTv.text = movie.release_date
            listBinding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->

                listener.onItemChecked(movie, isChecked)

            }
            for (favorite in favoriteList) {
                if (movie.id == favorite.id) {
                    isExist = true
                }
            }

            if (isExist) {
                listBinding.checkbox.isChecked = true
            } else
                listBinding.checkbox.isChecked = false
        }

        private fun gridBind(movie: Result) {
            var isExist = false
            val gridBinding = itemBinding as ItemMovieGridBinding
            gridBinding.titleTv.text = movie.title
            Picasso.get().load(Constants.IMAGE_BASE.plus(movie.poster_path))
                .into(gridBinding.movieIv)
            gridBinding.releaseDateTv.text = movie.release_date
            gridBinding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->

                listener.onItemChecked(movie, isChecked)

            }
            for (favorite in favoriteList) {
                if (movie.id == favorite.id) {
                    isExist = true
                }
            }

            if (isExist) {
                gridBinding.checkbox.isChecked = true
            } else
                gridBinding.checkbox.isChecked = false

        }

        override fun onClick(v: View?) {

            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { listener.onItemClick(it) }
            }

        }

        init {
            itemBinding.root.setOnClickListener(this)
        }

    }

    interface RecyclerViewEvent {
        fun onItemClick(movie: Result)
        fun onItemChecked(movie: Result, isChecked: Boolean)

    }

    class Diffcallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

}