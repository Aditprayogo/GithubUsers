package com.example.githubusers.feature.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.SearchView
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
        searchUsers()
        initViewModels()
        initRecyclerView()
        initObserver()
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun searchUsers() {
        sv_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if(query.isNotEmpty()) {
                        items.clear()
                        viewModel.getUserFromApi(query)
                        setIllustration(false)
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean = false

        })
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer {
            it?.let {
                handleStateLoading(it)
            }
        })
        viewModel.resultUserApi.observe(this, Observer {
            it?.let {
                handleUserFromApi(it)
            }
        })
    }

    private fun initRecyclerView() {
        rv_user.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_user.adapter = mainAdapter
    }

    private fun handleUserFromApi(result: List<UserSearchResponseItem>) {
        items.clear()
        items.addAll(result)
        mainAdapter.setItems(items)
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