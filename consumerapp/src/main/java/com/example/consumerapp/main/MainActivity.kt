package com.example.consumerapp.main

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumerapp.R
import com.example.consumerapp.data.UserFavorite
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchDataFromContentProvider()
    }

    private fun fetchDataFromContentProvider() {
        val table = "user_favorite_table"
        val authority = "com.example.githubusers.provider"

        val uri : Uri = Uri.Builder()
            .scheme("content")
            .authority(authority)
            .appendPath(table)
            .build()

        val contentResolver = this.contentResolver
        val cursor = contentResolver.query(
            uri,
            null,
            null,
            null,
            null
        )

        if (cursor != null) {
            initAdapter(cursor)
        } else {
            initAdapter(cursor)
        }
    }

    private fun initAdapter(cursor: Cursor?) {
        rc_user?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
        mainAdapter.setItems(convertCursor(cursor))
    }

    private fun convertCursor(cursor: Cursor?) : ArrayList<UserFavorite> {

        val userFavorite =  ArrayList<UserFavorite>()

        cursor?.apply {
            while (moveToNext()) {
                val username = getString(getColumnIndexOrThrow("username"))
                val avatarUrl = getString(getColumnIndexOrThrow("avatar_url"))
                val repositories = getString(getColumnIndexOrThrow("public_repos"))
                val followers = getString(getColumnIndexOrThrow("followers"))
                val following = getString(getColumnIndexOrThrow("following"))
                userFavorite.add(UserFavorite(username, avatarUrl, repositories, followers, following))
            }
        }

        return userFavorite
    }
}
