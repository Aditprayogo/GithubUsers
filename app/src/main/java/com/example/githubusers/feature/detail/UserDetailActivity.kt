package com.example.githubusers.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseActivity
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.data.entity.UserDetailResponse
import com.example.githubusers.feature.main.MainViewModel
import com.example.githubusers.feature.pager.ViewPagerAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_user_detail.*
import javax.inject.Inject

class UserDetailActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: UserDetailViewModel

    private var userDetail: UserDetailResponse? = null

    private var menu: Menu? = null

    private var username : String? = null

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

    fun getUsername() : String? {
        return username
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = username + "\'s Profile"

    }

    private fun initPageAdapter() {
        val sectionPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun fetchData() {
        username?.let {
            viewModel.getUserDetailFromApi(it)
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
    }

    private fun handleStateLoading(loading: LoaderState) {
        if(loading is LoaderState.ShowLoading) {
            //todo
        } else {
            //todo
        }
    }

    private fun handleResultUserDetail(data: UserDetailResponse) {
        userDetail = data
        txt_username.text = data.name
        txt_bio.text = data.bio ?: ""
        txt_follower.text = data.followers.toString()
        txt_following.text = data.following.toString()
        txt_repo.text = data.publicRepos.toString()
        Glide.with(this).load(data.avatarUrl).circleCrop().into(iv_user)
    }

    companion object {
        const val USERNAME_KEY = "username_key"
    }
}