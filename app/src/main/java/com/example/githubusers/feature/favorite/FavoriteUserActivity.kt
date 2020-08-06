package com.example.githubusers.feature.favorite

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseActivity
import com.example.githubusers.core.util.setGone
import com.example.githubusers.core.util.setVisible
import com.example.githubusers.data.db.entity.UserFavorite
import kotlinx.android.synthetic.main.activity_favorite_user.*
import javax.inject.Inject

class FavoriteUserActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: FavoriteUserViewModel

    private val listUser = mutableListOf<UserFavorite>()

    private val favoriteUserAdapter : FavoriteUserAdapter by lazy {
        FavoriteUserAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_user)
        initViewModels()
        initObserver()
        initRecyclerView()
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteUserViewModel::class.java]
    }

    private fun initRecyclerView() {
        rc_user.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rc_user.adapter = favoriteUserAdapter
    }

    private fun initObserver() {
        viewModel.resultUserFromDb.observe(this, Observer {
            handleUserFromDb(it)
        })
    }

    private fun handleUserFromDb(user: List<UserFavorite>) {
        handleEmptyUser(user)
        listUser.clear()
        listUser.addAll(user)
        favoriteUserAdapter.setItems(listUser)
    }

    private fun handleEmptyUser(user: List<UserFavorite>) {
        if (user.isEmpty()) {
            rc_user.setGone()
            base_empty.setVisible()
        } else {
            rc_user.setVisible()
            base_empty.setGone()
        }
    }
}