package com.test.btg.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.btg.ItemMovie
import com.test.btg.R

class MoviesAdapter(private val listMovies: List<ItemMovie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_filme, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieHolder).bind(listMovies[position])
    }

}