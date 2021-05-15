package com.aditprayogo.core.utils

import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
import com.aditprayogo.core.domain.model.UserFavorite

/**
 * Created by Aditiya Prayogo.
 */
object DataMapper {
    fun mapEntitiesToDomain(data: List<UserFavoriteEntity>): List<UserFavorite> =
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

    fun mapDomainToEntity(data: UserFavorite): UserFavoriteEntity =
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


}