package com.example.githubusers.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubusers.feature.detail.UserDetailViewModel
import com.example.githubusers.feature.favorite.FavoriteUserViewModel
import com.example.githubusers.feature.follower.FollowerViewModel
import com.example.githubusers.feature.following.FollowingViewModel
import com.example.githubusers.feature.main.MainViewModel
import com.example.githubusers.viewmodel.ViewModelFactory
import com.example.githubusers.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    internal abstract fun bindUserDetailViewModel(viewModel: UserDetailViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FollowerViewModel::class)
    internal abstract fun bindFollowerViewModel(viewModel: FollowerViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FollowingViewModel::class)
    internal abstract fun bindFollowingViewModel(viewModel: FollowingViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteUserViewModel::class)
    internal abstract fun bindFavoriteUserViewModel(viewModel: FavoriteUserViewModel) : ViewModel


}