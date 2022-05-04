package com.bakharaalief.huluapp.core.data.source.local.room

import androidx.room.*
import com.bakharaalief.huluapp.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from movies")
    fun getAllTourism(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where is_favorite = 1")
    fun getFavoriteTourism(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTourism(tourism: List<MovieEntity>)

    @Update
    fun updateFavoriteTourism(tourism: MovieEntity)
}