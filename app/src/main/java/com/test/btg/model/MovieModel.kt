package com.test.btg.model

import android.content.Context
import com.test.btg.database.LocalDataBaseFactory
import com.test.btg.database.entity.MovieDto
import com.test.btg.extension.alternateThreadAndBackToMain
import com.test.btg.network.MovieApi
import com.test.btg.network.MovieResponse
import com.test.btg.network.retrofit
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieModel {

    fun requestRemotePopularMovies(
        callbackSuccess: (it: MovieResponse) -> Unit,
        callbackThrowable: (it: Throwable) -> Unit
    ): Disposable {
        return retrofit
            .create(MovieApi::class.java)
            .getMovies()
            .alternateThreadAndBackToMain()
            .subscribe(callbackSuccess, callbackThrowable)
    }

    fun requestLocalFavoriteMovies(context: Context, callbackSuccess: (it: MutableList<MovieDto>) -> Unit): Disposable {
        return Observable
            .just(LocalDataBaseFactory::provideDatabase)
            .map { it(context).movieDao() }
            .map { it.fetchMovies() }
            .alternateThreadAndBackToMain(Schedulers.computation())
            .subscribe(callbackSuccess)
    }
}