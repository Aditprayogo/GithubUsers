package com.aditprayogo.core.domain.usecase

import com.aditprayogo.core.domain.model.*
import com.aditprayogo.core.utils.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface UserUseCase {
    suspend fun getUserFromApi(username : String) : Flow<ResultState<List<UserSearchItem>>>
    suspend fun getUserDetailFromApi(username : String) : Flow<ResultState<UserDetail>>
    suspend fun getUserFollowers(username : String) : Flow<ResultState<List<UserFollower>>>
    suspend fun getUserFollowing(username : String) :  Flow<ResultState<List<UserFollowing>>>

    fun fetchAllUserFavorite() : Flow<List<UserFavorite>>
    suspend fun deleteUserFromDb(userFavorite: UserFavorite)
    suspend fun addUserToFavDB(userFavorite: UserFavorite)
    fun getFavUserByUsername(username: String) : Flow<List<UserFavorite>>
}