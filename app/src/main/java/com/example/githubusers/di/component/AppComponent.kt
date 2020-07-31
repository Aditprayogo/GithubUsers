package com.example.githubusers.di.component

import android.app.Application
import com.example.githubusers.BaseApplication
import com.example.githubusers.di.module.ActivityBuilderModule
import com.example.githubusers.di.module.RoomModule
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
        RoomModule::class
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