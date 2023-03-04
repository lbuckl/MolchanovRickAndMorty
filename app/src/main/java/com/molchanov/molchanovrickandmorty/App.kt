package com.molchanov.molchanovrickandmorty

import android.app.Application
import android.content.Context
import com.molchanov.molchanovrickandmorty.di.AppComponent
import com.molchanov.molchanovrickandmorty.di.AppModule
import com.molchanov.molchanovrickandmorty.di.DaggerAppComponent

class App : Application() {

    companion object{
        lateinit var app: App
    }

    fun getContext(): Context = app.getContext()

    //Основной компоненты Dagger 2
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        app = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()

    }

}