package com.aditprayogo.core.di.module

import android.app.Application
import androidx.room.Room
import com.aditprayogo.core.data.local.db.AppDatabase
import com.aditprayogo.core.data.local.db.dao.UserFavoriteDao
import com.aditprayogo.core.data.remote.Network
import com.aditprayogo.core.data.remote.NetworkService
import com.aditprayogo.core.utils.const.databaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideNetworkService() : NetworkService {
        return Network.retrofitClient().create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application) : AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            databaseName
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserFavoriteDao(appDatabase: AppDatabase) : UserFavoriteDao {
        return appDatabase.userFavDao()
    }

}