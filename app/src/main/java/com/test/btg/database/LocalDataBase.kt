package com.test.btg.database

import androidx.room.*
import com.test.btg.database.entity.MovieDto
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


@Database(
    entities = [
        MovieDto::class
    ], version = 2
)
abstract class LocalDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}

@Dao
abstract class MovieDao {

    @Query("SELECT * FROM moviedto")
    abstract fun fetchMovies(): MutableList<MovieDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movieDto: MovieDto): Long

    fun insertAnotherThread(movieDto: MovieDto, dataBase: LocalDataBase) {
        Observable
            .just(movieDto)
            .doOnNext { dataBase.movieDao().insertMovie(movieDto) }
            .subscribeOn(Schedulers.computation())
            .subscribe()
    }


}