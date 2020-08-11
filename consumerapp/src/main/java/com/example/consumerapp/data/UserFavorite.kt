package com.example.consumerapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserFavorite(
    val username: String?,
    val avatar_url: String?,
    val company: String?,
    val public_repos: String?,
    val followers: String?,
    val following: String?,
    val location: String?
) : Parcelable