package com.test.btg.feature.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.test.btg.database.entity.MovieDto
import com.test.btg.feature.details.DetailsMovieActivity.Companion.EXTRA_MOVIE

class FavoriteFragment : MovieBaseFragment() {

    private val moviePresenter by lazy { MainPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviePresenter
            .requestFavoredMovies(this, requireContext()) {
                configureAdapter(it)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == MainActivity.REQUEST_CODE_FAVORITE_MOVIE &&
                resultCode == Activity.RESULT_OK) {
            data?.let {
                val itemMovie = data.getParcelableExtra(EXTRA_MOVIE) as MovieDto
                if(listMoviesDefault.contains(itemMovie)) return

                listMoviesDefault.add(0, itemMovie)
                if(adapter.listMovies == listMoviesDefault) {
                    adapter.notifyItemInserted(0)
                }
            }
        }
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}
