package com.example.githubusers.di.module

import com.example.githubusers.data.remote.NetworkService
import com.example.githubusers.di.DataScope
import com.example.githubusers.data.remote.Network
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @DataScope
    fun provideNetworkService() : NetworkService {
        return Network.retrofitClient().create(NetworkService::class.java)
    }

}