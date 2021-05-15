package com.aditprayogo.core.domain.usecase

import com.aditprayogo.core.utils.state.ResultState
import com.aditPrayogo.githubusers.utils.util.safeApiCall
import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
import com.aditprayogo.core.data.local.responses.SearchUserResponse
import com.aditprayogo.core.data.local.responses.UserDetailResponse
import com.aditprayogo.core.data.local.responses.UserFollowersResponse
import com.aditprayogo.core.data.local.responses.UserFollowingResponse
import com.aditprayogo.core.domain.model.UserFavorite
import com.aditprayogo.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserUseCase{
    /**
     * Remote
     */
    override suspend fun getUserFromApi(username: String) : ResultState<SearchUserResponse> {
        return safeApiCall {
            val response = userRepository.getUserFromApi(username)
            try {
                ResultState.Success(response.body())
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    override suspend fun getUserDetailFromApi(username: String) : ResultState<UserDetailResponse> {
        return safeApiCall {
            val response = userRepository.getDetailUserFromApi(username)
            try {
                ResultState.Success(response.body())
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    override suspend fun getUserFollowers(username: String) : ResultState<UserFollowersResponse> {
        return safeApiCall {
            val response = userRepository.getUserFollowers(username)
            try {
                ResultState.Success(response.body())
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    override suspend fun getUserFollowing(username: String) : ResultState<UserFollowingResponse> {
        return safeApiCall {
            val response = userRepository.getUserFollowing(username)
            try {
                ResultState.Success(response.body())
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    /**
     * Local
     */
    override fun fetchAllUserFavorite() = userRepository.fetchAllUserFavorite()

    override fun getFavUserByUsername(username: String) = userRepository.getFavoriteUserByUsername(username)

    override suspend fun deleteUserFromDb(userFavorite: UserFavorite) {
        try {
            userRepository.deleteUserFromFavDB(userFavorite)
        }catch (e: Exception) {
            throw Exception(e)
        }
    }

    override suspend fun addUserToFavDB(userFavorite: UserFavorite) {
        try {
            userRepository.addUserToFavDB(userFavorite)
        }catch (e: Exception) {
            throw Exception(e)
        }
    }


}