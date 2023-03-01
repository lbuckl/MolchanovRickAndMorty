package com.molchanov.molchanovrickandmorty.ui.main

import android.os.Bundle
import android.util.Log
import com.molchanov.molchanovrickandmorty.App
import com.molchanov.molchanovrickandmorty.R
import com.molchanov.molchanovrickandmorty.data.networkstatus.INetworkStatus
import com.molchanov.molchanovrickandmorty.databinding.ActivityMainBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseActivity
import com.molchanov.molchanovrickandmorty.ui.main.characters.CharactersFragment
import com.molchanov.molchanovrickandmorty.ui.main.episodes.EpisodesFragment
import com.molchanov.molchanovrickandmorty.ui.main.locations.LocationsFragment
import com.molchanov.molchanovrickandmorty.ui.router.IRouter
import javax.inject.Inject

/**
 * Основное активити приложения
 * реализует BottomNavigationView
 */
class MainActivity : BaseActivity<ActivityMainBinding>(){

    @Inject
    lateinit var networkStatus: INetworkStatus

    @Inject
    lateinit var router: IRouter

    override fun onCreate(savedInstanceState: Bundle?) {

        App.app.appComponent.inject(this@MainActivity)

        super.onCreate(savedInstanceState)

        initMenuListener()
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun addMainFragment() {
        router.addFragment(
            supportFragmentManager,
            binding.container.id,
            CharactersFragment.instance,
            CharactersFragment.FRAGMENT_TAG)

        this.supportActionBar?.title = resources.getString(R.string.characters)
    }

    private fun initMenuListener() {
        binding.bnvMain.menu.let { menu ->
            Log.v("@@@" , "menu")

            binding.bnvMain.setOnItemSelectedListener { item ->

                Log.v("@@@" , "setOnItemSelectedListener")
                when (item) {
                    menu.findItem(R.id.bv_item_characters) -> {

                        router.replaceFragment(
                            supportFragmentManager,
                            binding.container.id,
                            CharactersFragment.instance,
                            CharactersFragment.FRAGMENT_TAG
                        )

                        this.supportActionBar?.title = resources.getString(R.string.characters)
                    }
                    menu.findItem(R.id.bv_item_locations) -> {

                        router.replaceFragment(
                            supportFragmentManager,
                            binding.container.id,
                            LocationsFragment.instance,
                            LocationsFragment.FRAGMENT_TAG
                        )

                        this.supportActionBar?.title = resources.getString(R.string.locations)
                    }
                    menu.findItem(R.id.bv_item_episodes) -> {

                        router.replaceFragment(
                            supportFragmentManager,
                            binding.container.id,
                            EpisodesFragment.instance,
                            EpisodesFragment.FRAGMENT_TAG
                        )

                        this.supportActionBar?.title = resources.getString(R.string.episodes)
                    }
                }

                return@setOnItemSelectedListener true
            }
        }
    }
}