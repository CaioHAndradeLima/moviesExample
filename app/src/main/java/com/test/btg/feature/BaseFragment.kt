package com.test.btg.feature

import androidx.fragment.app.Fragment
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment(), DisposableManager {

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
}