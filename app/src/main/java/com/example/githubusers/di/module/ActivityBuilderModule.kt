package com.example.githubusers.di.module

import com.example.githubusers.ui.detail.UserDetailActivity
import com.example.githubusers.ui.favorite.FavoriteUserActivity
import com.example.githubusers.ui.main.MainActivity
import com.example.githubusers.ui.settings.SettingsActivity
import com.example.githubusers.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeUserDetailActivity() : UserDetailActivity

    @ContributesAndroidInjector
    abstract fun contributeFavoriteUserActivity() : FavoriteUserActivity

    @ContributesAndroidInjector
    abstract fun contributeSettingsActivity() : SettingsActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity() : SplashActivity

}