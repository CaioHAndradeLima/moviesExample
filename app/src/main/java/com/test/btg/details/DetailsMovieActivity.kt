package com.test.btg.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.test.btg.BaseActivity
import com.test.btg.ItemMovie
import com.test.btg.R
import com.test.btg.extension.addBackButtonInSupportActionBar
import com.test.btg.transition.TransitionHelper
import kotlinx.android.synthetic.main.activity_details_movie.*

class DetailsMovieActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        TransitionHelper.enableTransition( this )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie)
        addBackButtonInSupportActionBar()

        val itemMovie = intent.getParcelableExtra<ItemMovie>("EXTRA")

        Glide
            .with(this)
            .load(itemMovie.getCoverPathToDownload())
            .into(poster_transition)

        txt_title.text = itemMovie.title
        txt_description.text = itemMovie.description
    }

    companion object{
        fun intending(itemMovie: ItemMovie, context: Context): Intent =
            Intent(context, DetailsMovieActivity::class.java)
                .putExtra("EXTRA",itemMovie)
    }
}