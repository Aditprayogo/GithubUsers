package com.aditprayogo.core.di.module

import android.app.Application
import androidx.room.Room
import com.aditprayogo.core.data.local.db.AppDatabase
import com.aditprayogo.core.data.local.db.dao.UserFavoriteDao
import com.aditprayogo.core.data.remote.Network
import com.aditprayogo.core.data.remote.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideNetworkService() : NetworkService {
        return Network.retrofitClient().create(NetworkService::class.java)
    }

    private val mDatabaseName = "user_favorite_database"

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application) : AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            mDatabaseName
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserFavoriteDao(appDatabase: AppDatabase) : UserFavoriteDao {
        return appDatabase.userFavDao()
    }

//    @Provides
//    @Singleton
//    fun provideUserRepository(
//        service: NetworkService,
//        userFavoriteDao: UserFavoriteDao
//    ) : UserRepository {
//        return UserRepositoryImpl(
//            service,
//            userFavoriteDao
//        )
//    }

}