package com.test.btg.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.btg.R
import com.test.btg.database.entity.MovieDto
import com.test.btg.extension.alternateThreadAndBackToMain
import com.test.btg.extension.printStackTraceWhenDebug
import com.test.btg.feature.BaseFragment
import com.test.btg.feature.main.adapter.MoviesAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_movies.*

abstract class MovieBaseFragment : BaseFragment(), SearchListener {

    protected lateinit var adapter: MoviesAdapter
    protected lateinit var listMoviesDefault: MutableList<MovieDto>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    protected fun configureAdapter(it: MutableList<MovieDto>) {
        progress.visibility = View.GONE
        listMoviesDefault = it
        adapter = MoviesAdapter(listMoviesDefault)

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(
            recyclerview.context,
            DividerItemDecoration.VERTICAL
        )
        recyclerview.addItemDecoration(dividerItemDecoration)
        recyclerview.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String) {
        if (!::adapter.isInitialized) return

        val newListMovies = mutableListOf<MovieDto>()

        val disposable = Observable
            .fromArray(listMoviesDefault)
            .doOnNext {
                listMoviesDefault.forEach {
                    if (it.title != null &&
                        it.title!!.contains(query, true)
                    ) {
                        newListMovies.add(it)
                    } else if (isNeededSearchMovieByYearToo() &&
                        it.releaseDate != null &&
                        it.releaseDate!!.contains(query, true)
                    ) {
                        newListMovies.add(it)
                    }
                }
            }.alternateThreadAndBackToMain()
            .subscribe({
                adapter.listMovies = newListMovies
                adapter.notifyDataSetChanged()
            }) {
                it.printStackTraceWhenDebug()
            }

        addDisposable(disposable)
    }

    private fun isNeededSearchMovieByYearToo() = this is FavoriteFragment

    override fun useListDefault() {
        if (adapter.listMovies != listMoviesDefault) {
            adapter.listMovies = listMoviesDefault
            adapter.notifyDataSetChanged()
        }
    }

}