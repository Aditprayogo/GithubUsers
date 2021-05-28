package com.aditprayogo.core.data.local.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_favorite_table")
data class UserFavoriteEntity(
	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "username") val username: String,
	@ColumnInfo(name = "name") val name: String?,
	@ColumnInfo(name = "avatar_url") val avatarUrl: String?,
	@ColumnInfo(name = "following_url") val followingUrl: String?,
	@ColumnInfo(name = "bio") val bio: String?,
	@ColumnInfo(name = "company") val company: String?,
	@ColumnInfo(name = "public_repos") val publicRepos: Int?,
	@ColumnInfo(name = "followers_url") val followersUrl: String?,
	@ColumnInfo(name = "followers") val followers: Int?,
	@ColumnInfo(name = "following") val following: Int?,
	@ColumnInfo(name = "location") val location: String?
)
