package com.example.githubusers.di.module

import com.example.githubusers.data.network.NetworkService
import com.example.githubusers.di.DataScope
import com.example.githubusers.network.Network
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