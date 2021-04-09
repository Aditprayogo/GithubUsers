package com.example.githubusers.data.local.db.dao

import android.database.Cursor
import androidx.room.*
import com.example.githubusers.data.local.db.entity.UserFavorite

@Dao
interface UserFavoriteDao {

    @Query("SELECT * FROM user_favorite_table")
    suspend fun fetchAllUsers() : List<UserFavorite>

    @Query("SELECT * FROM user_favorite_table WHERE username = :userName")
    suspend fun getFavByUsername(userName: String) : List<UserFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserToFavoriteDB(user: UserFavorite)

    @Delete
    suspend fun deleteUserFromFavoriteDB(user: UserFavorite)

    /**
     * Cursor for content provider
     */
    @Query("SELECT * FROM user_favorite_table")
    fun cursorGetAllUserFavorite() : Cursor

}