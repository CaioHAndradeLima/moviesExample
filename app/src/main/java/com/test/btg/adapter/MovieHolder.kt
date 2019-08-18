package com.test.btg.adapter

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.btg.ItemMovie
import com.test.btg.R
import com.test.btg.details.DetailsMovieActivity
import com.test.btg.transition.TransitionHelper

class MovieHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private var title: TextView = view.findViewById(R.id.item_titulo) as TextView
    private var desc: TextView = view.findViewById(R.id.item_desc) as TextView
    private var releaseDate: TextView = view.findViewById(R.id.item_data) as TextView
    private var rating: RatingBar = view.findViewById(R.id.item_avaliacao) as RatingBar
    private var poster: ImageView = view.findViewById(R.id.poster_transition) as ImageView
    private lateinit var itemMovie: ItemMovie
    init {
        itemView.setOnClickListener(this)
    }

    fun bind(itemMovie: ItemMovie) {
        this.itemMovie = itemMovie
        title.text = itemMovie.title
        desc.text = itemMovie.description
        releaseDate.text = itemMovie.releaseDate
        rating.rating = itemMovie.voteAverage

        Glide
            .with(poster.context)
            .load(itemMovie.getCoverPathToDownload())
            .into(poster)
    }

    override fun onClick(v: View?) {
        val intent = DetailsMovieActivity.intending(itemMovie, poster.context)

        TransitionHelper.startActivity(
            poster.context as Activity,
            intent
        ) {
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                poster.context as Activity ,
                Pair.create(poster, "poster_transition")
            )
        }
    }

}
