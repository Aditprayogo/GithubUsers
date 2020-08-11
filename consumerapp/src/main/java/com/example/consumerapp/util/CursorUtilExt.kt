package com.example.consumerapp.util

import android.database.Cursor
import com.example.consumerapp.data.UserFavorite

fun Cursor.toListUserFavorite() : ArrayList<UserFavorite> {
    val userFavoriteList = ArrayList<UserFavorite>()

    apply {
        while (moveToNext()) {
            userFavoriteList.add(
                toUserFavoriteEntity()
            )
        }
    }

    return userFavoriteList
}

fun Cursor.toUserFavoriteEntity() : UserFavorite =
    UserFavorite(
        getString(getColumnIndexOrThrow("username")),
        getString(getColumnIndexOrThrow("avatar_url")),
        getString(getColumnIndexOrThrow("public_repos")),
        getString(getColumnIndexOrThrow("followers")),
        getString(getColumnIndexOrThrow("following"))
    )