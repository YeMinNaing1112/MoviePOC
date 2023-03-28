package com.yeminnaing.movieapplication.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yeminnaing.movieapplication.data.vos.GenreVo
import com.yeminnaing.movieapplication.data.vos.MovieVO

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movie: MovieVO?)

    @Query("SELECT * FROM movies")
    fun getAllMovie(): LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Long): LiveData<MovieVO?>

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMovieByType(type: String): LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies  WHERE id = :movieId")
    fun getMovieByIdOneTime(movieId: Long):MovieVO?

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    @Query("SELECT * FROM movies WHERE genres= :genres ")
    fun getMovieByGenre(genres: String):LiveData< List<MovieVO>>
}