package com.example.githubusers.feature.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseActivity
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.util.setGone
import com.example.githubusers.core.util.setVisible
import com.example.githubusers.data.entity.UserSearchResponseItem
import com.example.githubusers.feature.favorite.FavoriteUserActivity
import com.example.githubusers.feature.settings.MySettingsFragment
import com.example.githubusers.feature.settings.SettingsActivity
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favorite) {
            val intent = Intent(this, FavoriteUserActivity::class.java).also {
                startActivity(it)
            }
        }
        if (item.itemId == R.id.menu_settings) {
           val intent = Intent(this, SettingsActivity::class.java).also {
               startActivity(it)
           }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun searchUsers() {
        sv_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if(query.isNotEmpty()) {
                        items.clear()
                        viewModel.getUserFromApi(query)
                        sv_search.clearFocus()
                        setIllustration(false)
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean = false
        })

        sv_search.setOnCloseListener(object: SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                items.clear()
                mainAdapter.clearItems()
                setIllustration(true)
                return true
            }
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
        viewModel.networkError.observe(this, Observer {
            it?.let {
                handleStateInternet(it)
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

    private fun handleStateInternet(error: Boolean) {
        if(error) {
            baseLoading.setGone()
            rv_user.setGone()
        } else {
            baseLoading.setVisible()
            rv_user.setVisible()
        }
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
            base_empty.setVisible()
        } else {
            base_empty.setGone()
        }
    }
}