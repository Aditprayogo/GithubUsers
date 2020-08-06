package com.example.githubusers.domain

import com.example.githubusers.core.state.ResultState
import com.example.githubusers.core.util.SafeApiCall
import com.example.githubusers.data.db.entity.UserFavorite
import com.example.githubusers.data.entity.*
import com.example.githubusers.data.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    /**
     * Remote
     */
    suspend fun getUserFromApi(username: String) : ResultState<SearchUserResponse> {
        return SafeApiCall {
            val response = userRepository.getUserFromApi(username)
            try {
                ResultState.Success(response.body()!!)
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserDetailFromApi(username: String) : ResultState<UserDetailResponse> {
        return SafeApiCall {
            val response = userRepository.getDetailUserFromApi(username)
            try {
                ResultState.Success(response.body()!!)
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserFollowers(username: String) : ResultState<UserFollowersResponse> {
        return SafeApiCall {
            val response = userRepository.getUserFollowers(username)
            try {
                ResultState.Success(response.body()!!)
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserFollowing(username: String) : ResultState<UserFollowingResponse> {
        return SafeApiCall {
            val response = userRepository.getUserFollowing(username)
            try {
                ResultState.Success(response.body()!!)
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    /**
     * Local
     */
    suspend fun addUserToFavDB(userFavorite: UserFavorite) {
        try {
            userRepository.addUserToFavDB(userFavorite)
        }catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun getFavUserByUsername(username: String) : ResultState<List<UserFavorite>> {
        return try {
            val result = userRepository.getFavoriteUserByUsername(username)
            ResultState.Success(result)
        }catch (e: Exception) {
            ResultState.Error(e.localizedMessage, 500)
        }
    }
}