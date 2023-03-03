package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.ui.main.MainActivity
import com.molchanov.molchanovrickandmorty.ui.main.characters.CharactersFragment
import com.molchanov.molchanovrickandmorty.ui.main.characters.CharactersViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkStatusModule::class,
        RouterModule::class,
        VMFactoryModule::class,
        RepositoryRemoteModule::class
    ]
)


interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(charactersFragment: CharactersFragment)
    fun inject(charactersViewModel: CharactersViewModel)
}