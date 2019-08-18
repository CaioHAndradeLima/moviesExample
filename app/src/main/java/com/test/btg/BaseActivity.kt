package com.test.btg

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity(), DisposableManager {

    private val listDisposables = mutableListOf<Disposable>()

    override fun addDisposable(disposable: Disposable) {
        listDisposables.add(disposable)
    }

    override fun remove(disposable: Disposable) {
        listDisposables.remove(disposable)
    }

    override fun onDestroy() {
        listDisposables.forEach {
            if (!it.isDisposed)
                it.dispose()
        }
        listDisposables.clear()

        super.onDestroy()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (item.itemId) {
                android.R.id.home -> onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

interface DisposableManager {
    fun addDisposable(disposable: Disposable)
    fun remove(disposable: Disposable)
}