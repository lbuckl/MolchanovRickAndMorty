package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.ui.router.IRouter
import com.molchanov.molchanovrickandmorty.ui.router.Router
import dagger.Binds
import dagger.Module

@Module
abstract class RouterModule {

    @Binds
    abstract fun getRouter(router: Router): IRouter
}