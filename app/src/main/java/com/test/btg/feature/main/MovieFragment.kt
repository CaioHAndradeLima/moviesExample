package com.test.btg.feature.main

import android.os.Bundle
import android.view.View
import com.test.btg.extension.printStackTraceWhenDebug


class MovieFragment : MovieBaseFragment() {

    private val moviePresenter by lazy { MainPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviePresenter.requestMovies(this, {
            configureAdapter(it.result)
        }) {
            configureAdapter(mutableListOf())
            it.printStackTraceWhenDebug()
        }
    }

    companion object {
        fun newInstance() = MovieFragment()
    }

}
