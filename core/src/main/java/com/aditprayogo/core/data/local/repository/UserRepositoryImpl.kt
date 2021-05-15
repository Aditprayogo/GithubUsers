package com.aditprayogo.core.data.local.repository

import com.aditprayogo.core.data.local.db.dao.UserFavoriteDao
import com.aditprayogo.core.data.local.db.entity.UserFavorite
import com.aditprayogo.core.data.local.responses.SearchUserResponse
import com.aditprayogo.core.data.local.responses.UserDetailResponse
import com.aditprayogo.core.data.local.responses.UserFollowersResponse
import com.aditprayogo.core.data.local.responses.UserFollowingResponse
import com.aditprayogo.core.data.remote.NetworkService
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val userFavoriteDao: UserFavoriteDao
) : UserRepository {

    /**
     * Remote
     */
    override suspend fun getUserFromApi(username: String): Response<SearchUserResponse> {
        return networkService.getSearchUser(username)
    }

    override suspend fun getDetailUserFromApi(username: String): Response<UserDetailResponse> {
        return networkService.getDetailUser(username)
    }

    override suspend fun getUserFollowers(username: String): Response<UserFollowersResponse> {
        return networkService.getFollowerUser(username)
    }

    override suspend fun getUserFollowing(username: String): Response<UserFollowingResponse> {
        return networkService.getFollowingUser(username)
    }

    /**
     * Local
     */
    override suspend fun fetchAllUserFavorite(): List<UserFavorite> {
        return userFavoriteDao.fetchAllUsers()
    }

    override suspend fun getFavoriteUserByUsername(username: String): List<UserFavorite> {
        return userFavoriteDao.getFavByUsername(username)
    }

    override suspend fun addUserToFavDB(userFavorite: UserFavorite) {
        return userFavoriteDao.addUserToFavoriteDB(userFavorite)
    }

    override suspend fun deleteUserFromFavDB(userFavorite: UserFavorite) {
        return userFavoriteDao.deleteUserFromFavoriteDB(userFavorite)
    }

}