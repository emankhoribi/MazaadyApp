package com.example.domain.repo.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entity.data.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(favorite: Favorite): Long

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getFavorite(id: Int): Flow<Favorite>


    @Query("SELECT * FROM favorite ")
    fun getFavorites(): Flow<MutableList<Favorite>>

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE id = :id)")
    fun isRowIsExist(id : Int) : Flow<Boolean>


    @Delete
    suspend fun deleteRecord(favorite: Favorite)
}