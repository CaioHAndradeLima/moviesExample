package com.test.btg.feature.main

import android.content.Context
import com.test.btg.database.entity.MovieDto
import com.test.btg.feature.DisposableManager
import com.test.btg.model.MovieModel
import com.test.btg.network.MovieResponse

class MainPresenter {

    private val movieModel by lazy { MovieModel() }

    fun requestMovies(
        disposableManager: DisposableManager,
        callbackSuccess: (it: MovieResponse) -> Unit,
        callbackThrowable: (it: Throwable) -> Unit
    ) {

        val disposable = movieModel.requestRemotePopularMovies(
            callbackSuccess,
            callbackThrowable
        )

        disposableManager.addDisposable(disposable)
    }

    fun requestFavoredMovies(
        disposableManager: DisposableManager,
        context: Context,
        callbackSuccess: (it: MutableList<MovieDto>) -> Unit
    ) {

        val disposable = movieModel.requestLocalFavoriteMovies(
            context, callbackSuccess
        )

        disposableManager.addDisposable(disposable)
    }
}