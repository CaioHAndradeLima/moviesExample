package com.test.btg.feature.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.test.btg.R
import com.test.btg.extension.getFragmentBySupportFragmentManager
import com.test.btg.extension.hideSoftKeyboard
import com.test.btg.feature.BaseActivity
import com.test.btg.feature.main.MainPagerAdapter.Companion.POSITION_FAVORED_FRAGMENT
import com.test.btg.feature.main.MainPagerAdapter.Companion.POSITION_MOVIE_FRAGMENT
import com.test.btg.transition.TransitionHelper
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener {
    private lateinit var searchView: SearchView
    private var lastPositionViewPager = POSITION_MOVIE_FRAGMENT

    companion object {
        const val REQUEST_CODE_FAVORITE_MOVIE = 254
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TransitionHelper.enableTransition(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tablayout.setupWithViewPager(
            viewpager
        )
        viewpager.adapter = MainPagerAdapter(this)
        viewpager.addOnPageChangeListener(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        val searchViewMenuItem = menu.findItem(R.id.search)
        searchView = searchViewMenuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                (getFragmentBySupportFragmentManager(viewpager)
                        as SearchListener).onQueryTextSubmit(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })

        searchView.setOnCloseListener {
            closeSearchBar()
            false
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        (getFragmentBySupportFragmentManager(viewpager, position = POSITION_FAVORED_FRAGMENT)
                as FavoriteFragment).onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
            closeSearchBar()
        } else {
            super.onBackPressed()
        }
    }

    private fun closeSearchBar() {
        hideSoftKeyboard()
        (getFragmentBySupportFragmentManager(viewpager, lastPositionViewPager)
                as SearchListener).useListDefault()
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        if (searchView.isIconified) {
            lastPositionViewPager = position
            return
        }

        searchView.setQuery("", false)
        searchView.isIconified = true
        closeSearchBar()
        lastPositionViewPager = position
    }
}

interface SearchListener {
    fun onQueryTextSubmit(query: String)
    fun useListDefault()
}
