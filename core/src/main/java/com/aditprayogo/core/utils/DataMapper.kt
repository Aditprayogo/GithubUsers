package com.aditprayogo.core.utils

import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
import com.aditprayogo.core.data.local.responses.*
import com.aditprayogo.core.domain.model.*

/**
 * Created by Aditiya Prayogo.
 */
object DataMapper {

    fun mapUserSearchResponseToDomain(data: List<UserSearchResponseItem>): List<UserSearchItem> =
        data.map {
            UserSearchItem(
                avatarUrl = it.avatarUrl,
                id = it.id,
                login = it.login,
            )
        }

    fun mapUserFollowerResponseToDomain(data: List<UserFollowersResponseItem>): List<UserFollower> =
        data.map {
            UserFollower(
                avatarUrl = it.avatarUrl,
                id = it.id,
                login = it.login,
            )
        }

    fun mapUserFollowingResponseToDomain(data: List<UserFollowingResponseItem>): List<UserFollowing> =
        data.map {
            UserFollowing(
                avatarUrl = it.avatarUrl,
                id = it.id,
                login = it.login,
            )
        }

    fun mapUserDetailResponseToDomain(data: UserDetailResponse): UserDetail =
        UserDetail(
            username = data.login.toString(),
            name = data.name,
            avatarUrl = data.avatarUrl,
            followersUrl = data.followersUrl,
            bio = data.bio,
            company = data.company,
            publicRepos = data.publicRepos,
            followingUrl = data.followingUrl,
            followers = data.followers,
            following = data.following,
            location = data.location
        )


    fun mapUserFavoriteEntitiesToDomain(data: List<UserFavoriteEntity>): List<UserFavorite> =
        data.map {
            UserFavorite(
                username = it.username,
                name = it.name,
                avatarUrl = it.avatarUrl,
                followersUrl = it.followersUrl,
                bio = it.bio,
                company = it.company,
                publicRepos = it.publicRepos,
                followingUrl = it.followingUrl,
                followers = it.followers,
                following = it.following,
                location = it.location
            )
        }

    fun mapUserFavoriteDomainToEntity(data: UserFavorite): UserFavoriteEntity =
        UserFavoriteEntity(
            username = data.username,
            name = data.name,
            avatarUrl = data.avatarUrl,
            followersUrl = data.followersUrl,
            bio = data.bio,
            company = data.company,
            publicRepos = data.publicRepos,
            followingUrl = data.followingUrl,
            followers = data.followers,
            following = data.following,
            location = data.location
        )

    fun mapUserDetailToUserFavorite(it: UserDetail): UserFavorite =
        UserFavorite(
            username = it.username,
            name = it.name,
            avatarUrl = it.avatarUrl,
            followersUrl = it.followersUrl,
            bio = it.bio,
            company = it.company,
            publicRepos = it.publicRepos,
            followingUrl = it.followingUrl,
            followers = it.followers,
            following = it.following,
            location = it.location
        )


}