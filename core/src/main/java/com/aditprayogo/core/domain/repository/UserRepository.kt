package com.aditprayogo.core.domain.repository

import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
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
    suspend fun fetchAllUserFavorite() : List<UserFavoriteEntity>

    suspend fun getFavoriteUserByUsername(username: String) : List<UserFavoriteEntity>

    suspend fun addUserToFavDB(userFavoriteEntity: UserFavoriteEntity)

    suspend fun deleteUserFromFavDB(userFavoriteEntity: UserFavoriteEntity)



}