package com.example.githubusers.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [RoomModule::class])
abstract class StackRemoteViewsFactoryBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeStackRemoteViewsFactoryBuilder() : StackRemoteViewsFactoryModule
}