package com.aditprayogo.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditPrayogo.githubusers.R
import com.aditPrayogo.githubusers.di.FavoriteModuleDependencies
import com.aditPrayogo.githubusers.ui.settings.SettingsActivity
import com.aditprayogo.core.utils.viewUtils.setGone
import com.aditprayogo.core.utils.viewUtils.setVisible
import com.aditprayogo.core.domain.model.UserFavorite
import com.aditprayogo.favorite.databinding.ActivityFavoriteUserBinding
import com.aditprayogo.favorite.di.DaggerFavoriteComponent
import com.aditprayogo.favorite.viewModel.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteUserActivity : AppCompatActivity() {

    private val binding: ActivityFavoriteUserBinding by lazy {
        ActivityFavoriteUserBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteUserViewModel: FavoriteUserViewModel by viewModels {
        factory
    }

    private val listUser = mutableListOf<UserFavorite>()

    private val favoriteUserAdapter: FavoriteUserAdapter by lazy {
        FavoriteUserAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

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
        favoriteUserViewModel.fetchAllUserFavorite.observe(this, {
            handleUserFromDb(it)
        })
    }

    override fun onRestart() {
        super.onRestart()
        favoriteUserViewModel.fetchAllUserFavorite
    }

    private fun handleUserFromDb(userEntity: List<UserFavorite>) {
        handleEmptyUser(userEntity)
        listUser.clear()
        listUser.addAll(userEntity)
        favoriteUserAdapter.setItems(listUser)
    }

    private fun handleEmptyUser(userEntity: List<UserFavorite>) {
        if (userEntity.isEmpty()) {
            binding.rcUser.setGone()
            binding.baseEmpty.setVisible()
        } else {
            binding.rcUser.setVisible()
            binding.baseEmpty.setGone()
        }
    }
}