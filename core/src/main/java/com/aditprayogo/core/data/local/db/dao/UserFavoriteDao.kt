package com.aditprayogo.core.data.local.db.dao

import android.database.Cursor
import androidx.room.*
import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity

@Dao
interface UserFavoriteDao {

    @Query("SELECT * FROM user_favorite_table")
    suspend fun fetchAllUsers() : List<UserFavoriteEntity>

    @Query("SELECT * FROM user_favorite_table WHERE username = :userName")
    suspend fun getFavByUsername(userName: String) : List<UserFavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserToFavoriteDB(userEntity: UserFavoriteEntity)

    @Delete
    suspend fun deleteUserFromFavoriteDB(userEntity: UserFavoriteEntity)

    /**
     * Cursor for content provider
     */
    @Query("SELECT * FROM user_favorite_table")
    fun cursorGetAllUserFavorite() : Cursor

}