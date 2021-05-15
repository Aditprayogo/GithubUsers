package com.aditprayogo.core.domain.usecase

import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
import com.aditprayogo.core.data.local.responses.*
import com.aditprayogo.core.domain.model.UserFavorite
import com.aditprayogo.core.utils.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface UserUseCase {
    suspend fun getUserFromApi(username : String) : Flow<ResultState<List<UserSearchResponseItem>>>
    suspend fun getUserDetailFromApi(username : String) : Flow<ResultState<UserDetailResponse>>
    suspend fun getUserFollowers(username : String) : Flow<ResultState<List<UserFollowersResponseItem>>>
    suspend fun getUserFollowing(username : String) :  Flow<ResultState<List<UserFollowingResponseItem>>>

    fun fetchAllUserFavorite() : Flow<List<UserFavorite>>
    suspend fun deleteUserFromDb(userFavorite: UserFavorite)
    suspend fun addUserToFavDB(userFavorite: UserFavorite)
    fun getFavUserByUsername(username: String) : Flow<List<UserFavorite>>
}