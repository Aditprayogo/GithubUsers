package com.example.githubusers.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubusers.ui.detail.UserDetailViewModel
import com.example.githubusers.ui.favorite.FavoriteUserViewModel
import com.example.githubusers.ui.follower.FollowerViewModel
import com.example.githubusers.ui.following.FollowingViewModel
import com.example.githubusers.ui.main.MainViewModel
import com.example.githubusers.core.util.viewmodel_factory.ViewModelFactory
import com.example.githubusers.core.util.viewmodel_factory.ViewModelKey
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