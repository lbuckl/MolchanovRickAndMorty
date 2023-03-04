package com.molchanov.molchanovrickandmorty.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Singleton
    @Provides
    @Named("io")
    fun schedulersIO(): Scheduler = Schedulers.io()

    @Singleton
    @Provides
    @Named("main")
    fun schedulersMain(): Scheduler = AndroidSchedulers.mainThread()
}