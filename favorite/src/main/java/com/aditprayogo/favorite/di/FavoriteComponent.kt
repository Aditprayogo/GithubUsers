package com.aditprayogo.favorite.di

import android.content.Context
import com.aditPrayogo.githubusers.di.FavoriteModuleDependencies
import com.aditprayogo.favorite.ui.FavoriteUserActivity
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Aditiya Prayogo.
 */
@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(favoriteUserActivity: FavoriteUserActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context) : Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies) : Builder
        fun build() : FavoriteComponent
    }

}