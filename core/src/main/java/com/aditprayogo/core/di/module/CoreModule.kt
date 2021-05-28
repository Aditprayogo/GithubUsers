package com.aditprayogo.core.di.module

import android.app.Application
import androidx.room.Room
import com.aditprayogo.core.BuildConfig
import com.aditprayogo.core.data.local.db.AppDatabase
import com.aditprayogo.core.data.local.db.dao.UserFavoriteDao
import com.aditprayogo.core.data.remote.Network
import com.aditprayogo.core.data.remote.NetworkService
import com.aditprayogo.core.utils.const.databaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService {
        return Network.retrofitClient().create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): AppDatabase {
        val passPhare: ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSPHRASE.toCharArray())
        val factory = SupportFactory(passPhare)

        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            databaseName
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserFavoriteDao(appDatabase: AppDatabase): UserFavoriteDao {
        return appDatabase.userFavDao()
    }

}