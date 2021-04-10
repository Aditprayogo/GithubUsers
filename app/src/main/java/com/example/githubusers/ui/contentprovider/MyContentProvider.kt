package com.example.githubusers.ui.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.githubusers.data.local.db.dao.UserFavoriteDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

class MyContentProvider : ContentProvider() {

    @InstallIn(SingletonComponent::class)
    @EntryPoint
    interface UserFavoriteDaoContent {
        fun userFavDao(): UserFavoriteDao
    }

    companion object {
        private const val USER = 1
        private const val AUTHORITY = "com.example.githubusers.provider"
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "user_favorite_table", USER)
        }
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException()
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException()
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException()
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {

        val appContext = context?.applicationContext ?: throw IllegalStateException()
        val hiltEntryPoint =
            EntryPointAccessors.fromApplication(appContext, UserFavoriteDaoContent::class.java)
        val favoriteDao = hiltEntryPoint.userFavDao()

        return when (sUriMatcher.match(uri)) {
            USER -> favoriteDao.cursorGetAllUserFavorite()
            else -> null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        throw UnsupportedOperationException()
    }
}
