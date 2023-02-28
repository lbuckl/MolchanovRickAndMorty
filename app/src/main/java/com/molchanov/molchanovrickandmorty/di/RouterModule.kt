package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.ui.router.IRouter
import com.molchanov.molchanovrickandmorty.ui.router.Router
import dagger.Module
import dagger.Provides

@Module
class RouterModule {

    @Provides
    fun getRouter(): IRouter {
        return Router()
    }
}