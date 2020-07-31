package com.example.githubusers.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubusers.data.db.dao.UserFavoriteDao
import com.example.githubusers.data.db.entity.UserFavorite

@Database(
    entities = [UserFavorite::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userFavDao(): UserFavoriteDao

}