package com.aditprayogo.core.domain.repository

import com.aditprayogo.core.data.local.responses.UserDetailResponse
import com.aditprayogo.core.data.local.responses.UserFollowersResponseItem
import com.aditprayogo.core.data.local.responses.UserFollowingResponseItem
import com.aditprayogo.core.data.local.responses.UserSearchResponseItem
import com.aditprayogo.core.domain.model.UserFavorite
import com.aditprayogo.core.domain.model.UserSearchItem
import com.aditprayogo.core.utils.state.ResultState
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    /**
     * Remote
     */
    suspend fun getUserFromApi(username: String) : Flow<ResultState<List<UserSearchItem>>>

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