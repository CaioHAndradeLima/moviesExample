package com.test.btg.extension

import com.test.btg.BuildConfig


fun Throwable.printStackTraceWhenDebug() {
    if (BuildConfig.DEBUG)
        printStackTrace()
}