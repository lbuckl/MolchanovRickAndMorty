package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }
}