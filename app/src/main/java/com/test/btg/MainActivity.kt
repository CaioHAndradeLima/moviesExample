package com.test.btg

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.btg.adapter.MoviesAdapter
import com.test.btg.extension.alternateThreadAndBackToMain
import com.test.btg.network.MovieApi
import com.test.btg.network.provideRetrofit
import com.test.btg.transition.TransitionHelper
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.DividerItemDecoration



class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        TransitionHelper.enableTransition( this )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val disposable = provideRetrofit()
            .create(MovieApi::class.java)
            .getMovies()
            .alternateThreadAndBackToMain()
            .subscribe({
                progress.visibility = View.GONE

                recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                val dividerItemDecoration = DividerItemDecoration(
                    recyclerview.context,
                    DividerItemDecoration.VERTICAL
                )
                recyclerview.addItemDecoration(dividerItemDecoration)
                recyclerview.adapter = MoviesAdapter(it.result)
            }) {
                it.printStackTrace()
                Toast.makeText(this@MainActivity, "Erro na consulta de filmes", Toast.LENGTH_SHORT).show()
            }

        addDisposable(disposable)
    }
}
