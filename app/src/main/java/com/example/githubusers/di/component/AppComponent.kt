package com.example.githubusers.di.component

import android.app.Application
import com.example.githubusers.BaseApplication
import com.example.githubusers.di.module.*
import com.example.githubusers.feature.widget.StackRemoteViewsFactory
import dagger.BindsInstance
import dagger.Component
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
        FragmentBuilderModule::class,
        ContentProviderBuilderModule::class,
        StackRemoteViewsFactoryModule::class
    ]
)

interface AppComponent : AndroidInjector<BaseApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(stackRemoteViewsFactory: StackRemoteViewsFactory)
}