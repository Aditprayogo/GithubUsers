package com.aditprayogo.core.data

import com.aditprayogo.core.data.local.db.dao.UserFavoriteDao
import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
import com.aditprayogo.core.data.local.responses.SearchUserResponse
import com.aditprayogo.core.data.local.responses.UserDetailResponse
import com.aditprayogo.core.data.local.responses.UserFollowersResponse
import com.aditprayogo.core.data.local.responses.UserFollowingResponse
import com.aditprayogo.core.data.remote.NetworkService
import com.aditprayogo.core.domain.model.UserFavorite
import com.aditprayogo.core.domain.repository.UserRepository
import com.aditprayogo.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
    override fun fetchAllUserFavorite(): Flow<List<UserFavorite>> {
        return userFavoriteDao.fetchAllUsers().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteUserByUsername(username: String): Flow<List<UserFavorite>> {
        return userFavoriteDao.getFavByUsername(username).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun addUserToFavDB(userFavorite: UserFavorite) {
        val data = DataMapper.mapDomainToEntity(userFavorite)
        return userFavoriteDao.addUserToFavoriteDB(data)
    }

    override suspend fun deleteUserFromFavDB(userFavorite: UserFavorite) {
        val data = DataMapper.mapDomainToEntity(userFavorite)
        return userFavoriteDao.deleteUserFromFavoriteDB(data)
    }

}