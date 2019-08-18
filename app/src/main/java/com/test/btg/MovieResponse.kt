package com.test.btg

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    var result: MutableList<ItemMovie>
)