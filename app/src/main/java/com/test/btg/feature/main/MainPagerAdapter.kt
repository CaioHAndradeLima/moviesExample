package com.test.btg.feature.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.test.btg.R

class MainPagerAdapter(private val activity: AppCompatActivity) :
    FragmentPagerAdapter(activity.supportFragmentManager) {

    companion object {
        const val TOTAL_PAGES = 2
        const val POSITION_MOVIE_FRAGMENT = 0
        const val POSITION_FAVORED_FRAGMENT = 1
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            POSITION_MOVIE_FRAGMENT -> MovieFragment.newInstance()
            else -> FavoriteFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            POSITION_MOVIE_FRAGMENT -> activity.getString(R.string.tab_movie)
            else -> activity.getString(R.string.tab_movie_favorite)
        }
    }

    override fun getCount(): Int {
        return TOTAL_PAGES
    }

}
