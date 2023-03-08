package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.ui.main.MainActivity
import com.molchanov.molchanovrickandmorty.ui.main.characters.CharactersFragment
import com.molchanov.molchanovrickandmorty.ui.main.characters.CharactersViewModel
import com.molchanov.repository.di.RepositoryRemoteModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkStatusModule::class,
        RouterModule::class,
        VMFactoryModule::class,
        RepositoryRemoteModule::class,
        RepositoryLocalModule::class,
        SchedulersModule::class
    ]
)


interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(charactersFragment: CharactersFragment)
    fun inject(charactersViewModel: CharactersViewModel)
}