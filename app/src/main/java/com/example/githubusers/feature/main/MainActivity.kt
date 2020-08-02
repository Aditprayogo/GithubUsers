package com.example.githubusers.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseActivity
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.util.setGone
import com.example.githubusers.core.util.setVisible
import com.example.githubusers.data.entity.SearchUserResponse
import com.example.githubusers.data.entity.UserSearchResponseItem
import com.example.githubusers.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainViewModel

    private val items = mutableListOf<UserSearchResponseItem>()

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModels()
        initObserver()
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer {
            it?.let {
                handleStateLoading(it)
            }
        })
    }

    private fun initRecyclerView() {
        rv_user.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_user.adapter = mainAdapter
    }

    private fun handleStateLoading(loading: LoaderState) {
        if(loading is LoaderState.ShowLoading) {
            baseLoading.setVisible()
            setIllustration(false)
            rv_user.setGone()
        } else {
            baseLoading.setGone()
            setIllustration(false)
            rv_user.setVisible()
        }
    }

    private fun setIllustration(status: Boolean) {
        if(status) {
            iv_illustration.setVisible()
        } else {
            iv_illustration.setGone()
        }
    }
}