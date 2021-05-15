package com.aditprayogo.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aditprayogo.core.data.local.db.dao.UserFavoriteDao
import com.aditprayogo.core.data.local.db.entity.UserFavorite

@Database(
    entities = [UserFavorite::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userFavDao(): UserFavoriteDao

}