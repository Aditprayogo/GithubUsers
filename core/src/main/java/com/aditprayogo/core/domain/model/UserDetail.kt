package com.aditprayogo.core.domain.model

/**
 * Created by Aditiya Prayogo.
 */
data class UserDetail (
    val username: String,
    val name: String?,
    val avatarUrl: String?,
    val followingUrl: String?,
    val bio: String?,
    val company: String?,
    val publicRepos: Int?,
    val followersUrl: String?,
    val followers: Int?,
    val following: Int?,
    val location: String?
)