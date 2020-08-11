package com.example.githubusers.di.module

import android.app.Application
import com.example.githubusers.feature.widget.StackRemoteViewsFactory
import dagger.Module
import dagger.Provides

@Module(includes = [RoomModule::class])
class StackRemoteViewsFactoryModule {

    @Provides
    fun provideStackRemoteViewsFactory(
        app: Application
    ) : StackRemoteViewsFactory {
        return StackRemoteViewsFactory(app)
    }
}