package com.yeminnaing.movieapplication.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yeminnaing.movieapplication.data.vos.ActorVo
import com.yeminnaing.movieapplication.data.vos.MovieVO
import com.yeminnaing.movieapplication.persistence.daos.ActorsDao
import com.yeminnaing.movieapplication.persistence.daos.MovieDao

@Database(entities = [MovieVO::class, ActorVo::class], version = 3, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {
    companion object {
        const val DB_NAME = "THE_MOVIE_DB"

        var dbInstance: MovieDataBase? = null
        fun getDbInstance(context: Context): MovieDataBase? {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, MovieDataBase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorsDao
}
