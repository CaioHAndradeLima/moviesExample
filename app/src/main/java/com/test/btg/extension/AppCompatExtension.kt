package com.test.btg.extension

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.addBackButtonInSupportActionBar() {
    supportActionBar?.let {
        it.setDisplayHomeAsUpEnabled(true)
        it.setHomeButtonEnabled(true)
    }
}
