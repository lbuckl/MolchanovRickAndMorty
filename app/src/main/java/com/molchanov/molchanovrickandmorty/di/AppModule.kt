package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Singleton
    @Provides
    fun app(): App {
        return app
    }
}