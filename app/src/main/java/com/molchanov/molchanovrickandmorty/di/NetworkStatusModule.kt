package com.molchanov.molchanovrickandmorty.di

import com.molchanov.molchanovrickandmorty.App
import com.molchanov.molchanovrickandmorty.data.networkstatus.INetworkStatus
import com.molchanov.molchanovrickandmorty.data.networkstatus.NetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Модуль создаёт и возвращает средство мониторинга интернет соединения
 */
@Module
class NetworkStatusModule {

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = NetworkStatus(app.applicationContext)
}