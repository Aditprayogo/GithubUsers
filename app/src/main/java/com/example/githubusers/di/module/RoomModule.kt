package com.example.githubusers.di.module

import android.app.Application
import androidx.room.Room
import com.example.githubusers.data.db.AppDatabase
import com.example.githubusers.data.db.dao.UserFavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

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

}