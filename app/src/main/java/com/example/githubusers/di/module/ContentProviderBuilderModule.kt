package com.example.githubusers.di.module

import com.example.githubusers.ui.contentprovider.MyContentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [RoomModule::class])
abstract class ContentProviderBuilderModule  {

    @ContributesAndroidInjector
    abstract fun contributeContentProvider() : MyContentProvider
}