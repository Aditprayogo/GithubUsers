package com.example.githubusers.data.repository

import com.example.githubusers.data.db.dao.UserFavoriteDao
import com.example.githubusers.data.db.entity.UserFavorite
import com.example.githubusers.data.entity.SearchUserResponse
import com.example.githubusers.data.entity.UserDetailResponse
import com.example.githubusers.data.entity.UserFollowersResponse
import com.example.githubusers.data.entity.UserFollowingResponse
import com.example.githubusers.data.network.NetworkService
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