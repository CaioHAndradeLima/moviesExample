package com.test.btg.network

import com.test.btg.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getMovies(@Query("language") language : String = "pt-BR") : Observable<MovieResponse>
}