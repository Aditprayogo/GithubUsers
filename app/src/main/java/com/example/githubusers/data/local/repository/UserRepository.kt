package com.example.githubusers.data.local.repository

import com.example.githubusers.data.local.db.entity.UserFavorite
import com.example.githubusers.data.local.responses.SearchUserResponse
import com.example.githubusers.data.local.responses.UserDetailResponse
import com.example.githubusers.data.local.responses.UserFollowersResponse
import com.example.githubusers.data.local.responses.UserFollowingResponse
import retrofit2.Response

interface UserRepository {

    /**
     * Remote
     */
    suspend fun getUserFromApi(username: String) : Response<SearchUserResponse>

    suspend fun getDetailUserFromApi(username: String) : Response<UserDetailResponse>

    suspend fun getUserFollowers(username: String) : Response<UserFollowersResponse>

    suspend fun getUserFollowing(username: String) : Response<UserFollowingResponse>

    /**
     * Local
     */
    suspend fun fetchAllUserFavorite() : List<UserFavorite>

    suspend fun getFavoriteUserByUsername(username: String) : List<UserFavorite>

    suspend fun addUserToFavDB(userFavorite: UserFavorite)

    suspend fun deleteUserFromFavDB(userFavorite: UserFavorite)



}