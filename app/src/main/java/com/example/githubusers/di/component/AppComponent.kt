package com.example.githubusers.di.component

import android.app.Application
import android.content.Context
import com.example.githubusers.BaseApplication
import com.example.githubusers.di.module.*
import com.example.githubusers.feature.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        RoomModule::class,
        FragmentBuilderModule::class
    ]
)

interface AppComponent : AndroidInjector<BaseApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}