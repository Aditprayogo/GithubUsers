package com.aditPrayogo.githubusers.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditPrayogo.githubusers.R
import com.aditPrayogo.githubusers.utils.state.LoaderState
import com.aditPrayogo.githubusers.data.local.responses.UserSearchResponseItem
import com.aditPrayogo.githubusers.databinding.ActivityMainBinding
import com.aditPrayogo.githubusers.ui.favorite.FavoriteUserActivity
import com.aditPrayogo.githubusers.ui.settings.SettingsActivity
import com.aditPrayogo.githubusers.utils.util.setGone
import com.aditPrayogo.githubusers.utils.util.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val items = mutableListOf<UserSearchResponseItem>()

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(this)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToolbar()
        searchUsers()
        initRecyclerView()
        initObserver()
    }

    private fun initToolbar() {
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> startActivity(Intent(this, FavoriteUserActivity::class.java))
            R.id.menu_settings -> startActivity(Intent(this, SettingsActivity::class.java))
            R.id.menu_language -> startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun searchUsers() {
        binding.apply {
            svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (it.isNotEmpty()) {
                            items.clear()
                            mainViewModel.getUserFromApi(query)
                            svSearch.clearFocus()
                            setIllustration(false)
                        } else {
                            svSearch.clearFocus()
                            setIllustration(true)
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean = false
            })
            svSearch.setOnCloseListener {
                svSearch.setQuery("", false)
                svSearch.clearFocus()
                mainAdapter.clearItems()
                setIllustration(true)
                true
            }
        }
    }

    private fun initObserver() {
        with(mainViewModel) {
            state.observe(this@MainActivity, {
                it?.let {
                    handleStateLoading(it)
                }
            })
            resultUserApi.observe(this@MainActivity, {
                it?.let {
                    handleUserFromApi(it)
                }
            })
            networkError.observe(this@MainActivity, {
                it?.let {
                    handleStateInternet(it)
                }
            })
        }
    }

    private fun initRecyclerView() {
        binding.rvUser.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mainAdapter
        }
        mainAdapter.setActivity(this)
    }

    private fun handleUserFromApi(result: List<UserSearchResponseItem>) {
        items.clear()
        items.addAll(result)
        mainAdapter.setItems(items)
    }

    private fun handleStateInternet(error: Boolean) {
        with(binding) {
            if (error) {
                baseLoading.root.setVisible()
                rvUser.setGone()
            } else {
                baseLoading.root.setGone()
                rvUser.setVisible()
            }
        }

    }

    private fun handleStateLoading(loading: LoaderState) {
        with(binding) {
            if (loading is LoaderState.ShowLoading) {
                baseLoading.root.setVisible()
                setIllustration(false)
                rvUser.setGone()
            } else {
                baseLoading.root.setGone()
                setIllustration(false)
                rvUser.setVisible()
            }
        }

    }

    private fun setIllustration(status: Boolean) {
        binding.baseEmpty.root.visibility = if (status) VISIBLE else INVISIBLE
    }
}