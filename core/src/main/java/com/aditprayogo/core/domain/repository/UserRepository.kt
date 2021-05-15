package com.aditprayogo.core.domain.repository

import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
import com.aditprayogo.core.data.local.responses.SearchUserResponse
import com.aditprayogo.core.data.local.responses.UserDetailResponse
import com.aditprayogo.core.data.local.responses.UserFollowersResponse
import com.aditprayogo.core.data.local.responses.UserFollowingResponse
import com.aditprayogo.core.domain.model.UserFavorite
import kotlinx.coroutines.flow.Flow
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
    fun fetchAllUserFavorite() : Flow<List<UserFavorite>>

    fun getFavoriteUserByUsername(username: String) : Flow<List<UserFavorite>>

    suspend fun addUserToFavDB(userFavoriteEntity: UserFavorite)

    suspend fun deleteUserFromFavDB(userFavoriteEntity: UserFavorite)



}