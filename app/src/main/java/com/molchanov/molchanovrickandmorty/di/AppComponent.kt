package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkStatusModule::class,
        RouterModule::class,
        VMFactoryModule::class
    ]
)


interface AppComponent {
    fun inject(mainActivity: MainActivity)
}