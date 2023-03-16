package com.yeminnaing.movieapplication.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yeminnaing.movieapplication.data.vos.ActorVo

@Dao
interface ActorsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActors(list: List<ActorVo>)

    @Query("SELECT * FROM actors")
    fun getAllActors(): List<ActorVo>
}