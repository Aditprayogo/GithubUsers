package com.example.githubusers.di.component

import android.app.Application
import com.example.githubusers.BaseApplication
import com.example.githubusers.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilderModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        RoomModule::class,
        DataModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)

interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }
}