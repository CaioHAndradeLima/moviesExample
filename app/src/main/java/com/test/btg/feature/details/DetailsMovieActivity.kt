package com.test.btg.feature.details

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.test.btg.R
import com.test.btg.database.LocalDataBaseFactory
import com.test.btg.database.entity.MovieDto
import com.test.btg.extension.addBackButtonInSupportActionBar
import com.test.btg.feature.BaseActivity
import com.test.btg.transition.TransitionHelper
import kotlinx.android.synthetic.main.activity_details_movie.*


class DetailsMovieActivity : BaseActivity(), View.OnClickListener {

    private val itemMovie by lazy { intent.getParcelableExtra<MovieDto>(EXTRA_MOVIE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        TransitionHelper.enableTransition(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie)
        addBackButtonInSupportActionBar()

        Glide
            .with(this)
            .load(itemMovie.getCoverPathToDownload())
            .into(poster_transition)

        txt_title.text = itemMovie.title
        txt_description.text = itemMovie.description

        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val dataBase = LocalDataBaseFactory.provideDatabase(this)
        dataBase
            .movieDao()
            .insertAnotherThread(itemMovie, dataBase)
        Toast.makeText(this, getString(R.string.favored_movie), Toast.LENGTH_SHORT).show()

        setResult(Activity.RESULT_OK, Intent()
            .putExtra(EXTRA_MOVIE, itemMovie))
    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"

        fun intending(movieDto: MovieDto, context: Context): Intent =
            Intent(context, DetailsMovieActivity::class.java)
                .putExtra(EXTRA_MOVIE, movieDto)
    }
}