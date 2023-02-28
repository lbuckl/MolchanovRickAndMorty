package com.molchanov.molchanovrickandmorty.ui.main

import android.os.Bundle
import com.molchanov.molchanovrickandmorty.App
import com.molchanov.molchanovrickandmorty.R
import com.molchanov.molchanovrickandmorty.data.networkstatus.INetworkStatus
import com.molchanov.molchanovrickandmorty.databinding.ActivityMainBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseActivity
import com.molchanov.molchanovrickandmorty.ui.router.IRouter
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>(){

    @Inject
    lateinit var networkStatus: INetworkStatus

    @Inject
    lateinit var router: IRouter

    override fun onCreate(savedInstanceState: Bundle?) {

        App.app.appComponent.inject(this@MainActivity)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun addMainFragment() {
        router.addFragment(
            supportFragmentManager,
            binding.container.id,
            MainFragment.instance,
            MainFragment.FRAGMENT_TAG)
    }
}