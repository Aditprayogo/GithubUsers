package com.aditprayogo.core.domain.repository

import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
import com.aditprayogo.core.data.local.responses.*
import com.aditprayogo.core.domain.model.UserFavorite
import com.aditprayogo.core.utils.state.ResultState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepository {

    /**
     * Remote
     */
    suspend fun getUserFromApi(username: String) : Flow<ResultState<List<UserSearchResponseItem>>>

    suspend fun getDetailUserFromApi(username: String) : Flow<ResultState<UserDetailResponse>>

    suspend fun getUserFollowers(username: String) : Flow<ResultState<List<UserFollowersResponseItem>>>

    suspend fun getUserFollowing(username: String) : Flow<ResultState<List<UserFollowingResponseItem>>>

    /**
     * Local
     */
    fun fetchAllUserFavorite() : Flow<List<UserFavorite>>

    fun getFavoriteUserByUsername(username: String) : Flow<List<UserFavorite>>

    suspend fun addUserToFavDB(userFavoriteEntity: UserFavorite)

    suspend fun deleteUserFromFavDB(userFavoriteEntity: UserFavorite)



}