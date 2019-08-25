package com.test.btg.feature.main.adapter

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.btg.R
import com.test.btg.database.entity.MovieDto
import com.test.btg.feature.details.DetailsMovieActivity
import com.test.btg.feature.main.MainActivity.Companion.REQUEST_CODE_FAVORITE_MOVIE
import com.test.btg.transition.TransitionHelper

class MovieHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private var title: TextView = view.findViewById(R.id.item_titulo) as TextView
    private var desc: TextView = view.findViewById(R.id.item_desc) as TextView
    private var releaseDate: TextView = view.findViewById(R.id.item_data) as TextView
    private var rating: RatingBar = view.findViewById(R.id.item_avaliacao) as RatingBar
    private var poster: ImageView = view.findViewById(R.id.poster_transition) as ImageView
    private lateinit var movieDto: MovieDto

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(movieDto: MovieDto) {
        this.movieDto = movieDto
        title.text = movieDto.title
        desc.text = movieDto.description
        releaseDate.text = movieDto.releaseDate
        rating.rating = movieDto.voteAverage

        Glide
            .with(poster.context)
            .load(movieDto.getCoverPathToDownload())
            .into(poster)
    }

    override fun onClick(v: View?) {
        val intent = DetailsMovieActivity.intending(movieDto, poster.context)

        TransitionHelper.startActivityForResult(
            poster.context as Activity,
            intent,
            REQUEST_CODE_FAVORITE_MOVIE
        ) {
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                poster.context as Activity,
                Pair.create(poster, "poster_transition")
            )
        }
    }

}
