package com.example.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entity.data.Favorite
import com.example.domain.repo.db.FavoriteDao


@Database(entities = [Favorite::class], version = 1)
abstract class MoviesFavoritesDatabase : RoomDatabase(){

    abstract fun FavoriteDao(): FavoriteDao
}