package com.aditprayogo.core.data.remote

import com.aditprayogo.core.BuildConfig
import com.aditprayogo.core.data.local.responses.SearchUserResponse
import com.aditprayogo.core.data.local.responses.UserDetailResponse
import com.aditprayogo.core.data.local.responses.UserFollowersResponse
import com.aditprayogo.core.data.local.responses.UserFollowingResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    /**
     * Endpoints search User
     */
    @GET("search/users?")
    suspend fun getSearchUser(
        @Query("q") q : String
    ) : SearchUserResponse

    /**
     * Endpoints Detail User
     */
    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ) : UserDetailResponse

    /**
     * Endpoints Followers
     */
    @GET("users/{username}/followers")
    suspend fun getFollowerUser(
        @Path("username") username: String
    ) : UserFollowersResponse

    /**
     * Endpoints Following
     */
    @GET("users/{username}/following")
    suspend fun getFollowingUser(
        @Path("username") username: String
    ) : UserFollowingResponse


}