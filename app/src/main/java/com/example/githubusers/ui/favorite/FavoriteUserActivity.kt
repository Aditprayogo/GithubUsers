package com.example.githubusers.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseActivity
import com.example.githubusers.data.local.db.entity.UserFavorite
import com.example.githubusers.databinding.ActivityFavoriteUserBinding
import com.example.githubusers.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_favorite_user.*
import javax.inject.Inject

class FavoriteUserActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val binding: ActivityFavoriteUserBinding by lazy {
        ActivityFavoriteUserBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: FavoriteUserViewModel

    private val listUser = mutableListOf<UserFavorite>()

    private val favoriteUserAdapter: FavoriteUserAdapter by lazy {
        FavoriteUserAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewModels()
        initObserver()
        initRecyclerView()
        initToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            elevation = 0f
            title = getString(R.string.favorite_user)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteUserViewModel::class.java]
    }

    private fun initRecyclerView() {
        binding.rcUser.apply {
            layoutManager =
                LinearLayoutManager(this@FavoriteUserActivity, LinearLayoutManager.VERTICAL, false)
            adapter = favoriteUserAdapter
        }
    }

    private fun initObserver() {
        viewModel.resultUserFromDb.observe(this, {
            handleUserFromDb(it)
        })
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.fetchAllUserFavorite()
    }

    private fun handleUserFromDb(user: List<UserFavorite>) {
        handleEmptyUser(user)
        listUser.clear()
        listUser.addAll(user)
        favoriteUserAdapter.setItems(listUser)
    }

    private fun handleEmptyUser(user: List<UserFavorite>) {
        if (user.isEmpty()) {
            binding.rcUser.setGone()
            binding.baseEmpty.root.setVisible()
        } else {
            binding.rcUser.setVisible()
            binding.baseEmpty.root.setGone()
        }
    }
}