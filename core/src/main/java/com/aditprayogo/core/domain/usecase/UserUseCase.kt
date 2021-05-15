package com.aditprayogo.core.domain.usecase

import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
import com.aditprayogo.core.data.local.responses.SearchUserResponse
import com.aditprayogo.core.data.local.responses.UserDetailResponse
import com.aditprayogo.core.data.local.responses.UserFollowersResponse
import com.aditprayogo.core.data.local.responses.UserFollowingResponse
import com.aditprayogo.core.domain.model.UserFavorite
import com.aditprayogo.core.utils.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface UserUseCase {
    suspend fun getUserFromApi(username : String) : ResultState<SearchUserResponse>
    suspend fun getUserDetailFromApi(username : String) : ResultState<UserDetailResponse>
    suspend fun getUserFollowers(username : String) : ResultState<UserFollowersResponse>
    suspend fun getUserFollowing(username : String) : ResultState<UserFollowingResponse>

    fun fetchAllUserFavorite() : Flow<List<UserFavorite>>
    suspend fun deleteUserFromDb(userFavorite: UserFavorite)
    suspend fun addUserToFavDB(userFavorite: UserFavorite)
    fun getFavUserByUsername(username: String) : Flow<List<UserFavorite>>
}