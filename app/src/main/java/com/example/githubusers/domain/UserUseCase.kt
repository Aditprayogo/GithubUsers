package com.example.githubusers.domain

import com.example.githubusers.core.state.ResultState
import com.example.githubusers.core.util.SafeApiCall
import com.example.githubusers.data.entity.SearchUserResponse
import com.example.githubusers.data.entity.UserDetailResponse
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
}