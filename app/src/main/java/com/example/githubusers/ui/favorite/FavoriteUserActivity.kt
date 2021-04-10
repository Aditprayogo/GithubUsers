package com.example.githubusers.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.R
import com.example.githubusers.core.util.setGone
import com.example.githubusers.core.util.setVisible
import com.example.githubusers.data.local.db.entity.UserFavorite
import com.example.githubusers.databinding.ActivityFavoriteUserBinding
import com.example.githubusers.ui.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_favorite_user.*

@AndroidEntryPoint
class FavoriteUserActivity : AppCompatActivity() {

    private val binding: ActivityFavoriteUserBinding by lazy {
        ActivityFavoriteUserBinding.inflate(layoutInflater)
    }

    private val favoriteUserViewModel: FavoriteUserViewModel by viewModels()

    private val listUser = mutableListOf<UserFavorite>()

    private val favoriteUserAdapter: FavoriteUserAdapter by lazy {
        FavoriteUserAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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

    private fun initRecyclerView() {
        binding.rcUser.apply {
            layoutManager =
                LinearLayoutManager(this@FavoriteUserActivity, LinearLayoutManager.VERTICAL, false)
            adapter = favoriteUserAdapter
        }
    }

    private fun initObserver() {
        favoriteUserViewModel.resultUserFromDb.observe(this, {
            handleUserFromDb(it)
        })
    }

    override fun onRestart() {
        super.onRestart()
        favoriteUserViewModel.fetchAllUserFavorite()
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