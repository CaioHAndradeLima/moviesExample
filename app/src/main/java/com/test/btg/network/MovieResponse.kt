package com.test.btg.network

import com.google.gson.annotations.SerializedName
import com.test.btg.database.entity.MovieDto

data class MovieResponse(
    @SerializedName("results")
    var result: MutableList<MovieDto>
)