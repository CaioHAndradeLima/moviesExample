package com.test.btg.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.btg.R
import com.test.btg.database.entity.MovieDto

class MoviesAdapter(var listMovies: MutableList<MovieDto>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_EMPTY = 0
        private const val VIEW_TYPE_MOVIE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_MOVIE-> MovieHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_filme, parent, false)
            )
            else -> NotFoundMovieHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_not_found_filme, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return if(listMovies.isNotEmpty()) {
            listMovies.size
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(listMovies.isEmpty()) {
            VIEW_TYPE_EMPTY
        } else {
            VIEW_TYPE_MOVIE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieHolder).bind(listMovies[position])
    }

}