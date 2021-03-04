package com.example.githubusers.feature.detail

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseActivity
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.util.setGone
import com.example.githubusers.core.util.setVisible
import com.example.githubusers.core.util.toast
import com.example.githubusers.data.db.entity.UserFavorite
import com.example.githubusers.data.entity.UserDetailResponse
import com.example.githubusers.feature.favorite.FavoriteUserActivity
import com.example.githubusers.feature.pager.ViewPagerAdapter
import com.example.githubusers.feature.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_user_detail.*
import javax.inject.Inject

class UserDetailActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: UserDetailViewModel

    private var userDetail: UserDetailResponse? = null

    private var userFavoriteEntity: UserFavorite? = null

    private var favoriteActive = false

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        handleIntent()
        initViewModels()
        initObserver()
        fetchData()
        initToolbar()
        initPageAdapter()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun getUsername(): String? {
        return username
    }

    private fun initToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            elevation = 0f
            title = "$username\'s Profile"
        }

        fav_button.setOnClickListener {
            setFavoriteUser()
        }
    }

    private fun initPageAdapter() {
        val sectionPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.menu_favorite -> {
                startActivity(Intent(this, FavoriteUserActivity::class.java))
            }
            R.id.menu_language -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun fetchData() {
        username?.let {
            viewModel.getUserDetailFromApi(it)
            viewModel.getFavUserByUsername(it)
        }
    }

    private fun handleIntent() {
        username = intent.getStringExtra(USERNAME_KEY) as String
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[UserDetailViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer {
            handleStateLoading(it)
        })
        viewModel.resultUserDetail.observe(this, Observer {
            handleResultUserDetail(it)
        })
        viewModel.resultUserDetailFromDb.observe(this, Observer {
            handleUserDetailFromDb(it)
        })
        viewModel.resultInsertUserDb.observe(this, Observer {
            if (it) {
                username?.let {
                    viewModel.getFavUserByUsername(it)
                }
                toast(getString(R.string.user_success))
            }
        })
        viewModel.resultDeleteFromDb.observe(this, Observer {
            if (it) {
                username?.let {
                    viewModel.getFavUserByUsername(it)
                }
                toast(getString(R.string.user_deleted))
            }
        })
    }

    private fun setFavoriteUser() {
        if (favoriteActive) {
            userFavoriteEntity?.let {
                viewModel.deleteUserFromDb(it)
            }
        } else {
            val userFavorite = userDetail?.login?.let {
                UserFavorite(
                    username = it,
                    name = userDetail?.name,
                    avatarUrl = userDetail?.avatarUrl,
                    followingUrl = userDetail?.followingUrl,
                    bio = userDetail?.bio,
                    company = userDetail?.company,
                    publicRepos = userDetail?.publicRepos,
                    followersUrl = userDetail?.followersUrl,
                    followers = userDetail?.followers,
                    following = userDetail?.following,
                    location = userDetail?.location
                )
            }
            userFavorite?.let { viewModel.addUserToFavDB(it) }
        }
    }

    private fun handleUserDetailFromDb(userFavorite: List<UserFavorite>) {
        if (userFavorite.isEmpty()) {
            favoriteActive = false
            val icon = R.drawable.ic_baseline_favorite_border_24
            fav_button.setImageResource(icon)
        } else {
            userFavoriteEntity = userFavorite.first()
            favoriteActive = true
            val icon = R.drawable.ic_baseline_favorite_24
            fav_button.setImageResource(icon)
        }
    }

    private fun handleStateLoading(loading: LoaderState) {
        if (loading is LoaderState.ShowLoading) {
            fav_button.setGone()
        } else {
            fav_button.setVisible()
        }
    }

    private fun handleResultUserDetail(data: UserDetailResponse) {
        userDetail = data
        txt_username.text = data.login
        txt_bio.text = data.bio ?: getString(R.string.no_bio)
        txt_follower.text = data.followers.toString()
        txt_following.text = data.following.toString()
        txt_repo.text = data.publicRepos.toString()
        Glide.with(this).load(data.avatarUrl).circleCrop().into(iv_user)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    companion object {
        const val USERNAME_KEY = "username_key"
    }
}