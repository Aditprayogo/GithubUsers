package com.aditprayogo.core.data.local.repository

import com.aditprayogo.core.data.local.db.entity.UserFavorite
import com.aditprayogo.core.data.local.responses.SearchUserResponse
import com.aditprayogo.core.data.local.responses.UserDetailResponse
import com.aditprayogo.core.data.local.responses.UserFollowersResponse
import com.aditprayogo.core.data.local.responses.UserFollowingResponse
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