package com.example.githubusers.di.module

import com.example.githubusers.ui.follower.FollowerFragment
import com.example.githubusers.ui.following.FollowingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeFollowingFragment() : FollowingFragment

    @ContributesAndroidInjector
    abstract fun contributeFollowerFragment() : FollowerFragment
}