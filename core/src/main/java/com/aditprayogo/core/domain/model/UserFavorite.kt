package com.aditprayogo.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Aditiya Prayogo.
 */
@Parcelize
data class UserFavorite(
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
) : Parcelable