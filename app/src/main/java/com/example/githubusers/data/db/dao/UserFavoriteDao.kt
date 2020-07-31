package com.example.githubusers.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.githubusers.data.db.entity.UserFavorite
import dagger.multibindings.IntoSet
import retrofit2.http.DELETE

@Dao
interface UserFavoriteDao {

    @Query("SELECT * FROM user_favorite_table")
    suspend fun fetchAllUsers() : List<UserFavorite>

    @Query("SELECT * FROM user_favorite_table WHERE username = :userName")
    suspend fun getFavByUsername(userName: String) : List<UserFavorite>

    @Insert
    suspend fun addUserToFavoriteDB(user: UserFavorite)

    @Delete
    suspend fun deleteUserFromFavoriteDB(user: UserFavorite)

}