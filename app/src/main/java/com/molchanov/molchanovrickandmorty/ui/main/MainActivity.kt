package com.molchanov.molchanovrickandmorty.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.molchanov.molchanovrickandmorty.App
import com.molchanov.molchanovrickandmorty.R
import com.molchanov.molchanovrickandmorty.data.networkstatus.INetworkStatus
import com.molchanov.molchanovrickandmorty.databinding.ActivityMainBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseActivity
import com.molchanov.molchanovrickandmorty.ui.main.characters.CharactersFragment
import com.molchanov.molchanovrickandmorty.ui.main.episodes.EpisodesFragment
import com.molchanov.molchanovrickandmorty.ui.main.locations.LocationsFragment
import com.molchanov.molchanovrickandmorty.ui.router.IRouter
import com.molchanov.molchanovrickandmorty.utils.vision
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

/**
 * Основное активити приложения
 * реализует BottomNavigationView
 */
class MainActivity : BaseActivity<ActivityMainBinding>(){

    @Inject
    lateinit var networkStatus: INetworkStatus

    @Inject @Named("io")
    lateinit var ioFlow: Scheduler

    @Inject @Named("main")
    lateinit var mainFlow: Scheduler

    @Inject
    lateinit var router: IRouter

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {

        App.app.appComponent.inject(this@MainActivity)

        super.onCreate(savedInstanceState)

        initMenuListener()

        initNetworkChecker()
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun addMainFragment() {

        naviGoToCharacterFragment()
    }

    private fun initMenuListener() {

        binding.bnvMain.menu.let { menu ->

            binding.bnvMain.setOnItemSelectedListener { item ->

                when (item) {
                    menu.findItem(R.id.bv_item_characters) -> {

                        naviGoToCharacterFragment()
                    }
                    menu.findItem(R.id.bv_item_locations) -> {

                        naviGoToLocationFragment()
                    }
                    menu.findItem(R.id.bv_item_episodes) -> {

                        naviGoToEpisodesFragment()
                    }
                }

                return@setOnItemSelectedListener true
            }
        }
    }

    private fun naviGoToCharacterFragment(){

        with(binding){
            router.addFragment(
                supportFragmentManager,
                container.id,
                CharactersFragment.instance,
                CharactersFragment.FRAGMENT_TAG)

            abMainActivityTvHeader.text = resources.getString(R.string.characters)

            abMainActivityIcHome.vision(View.GONE)

            abMainActivityIcBack.vision(View.GONE)
        }
    }

    private fun naviGoToLocationFragment(){

        with(binding){
            router.replaceFragment(
                supportFragmentManager,
                container.id,
                LocationsFragment.instance,
                LocationsFragment.FRAGMENT_TAG
            )

            abMainActivityTvHeader.text = resources.getString(R.string.locations)

            abMainActivityIcHome.vision(View.VISIBLE)

            abMainActivityIcBack.vision(View.GONE)
        }
    }

    private fun naviGoToEpisodesFragment(){

        with(binding){
            router.replaceFragment(
                supportFragmentManager,
                container.id,
                EpisodesFragment.instance,
                EpisodesFragment.FRAGMENT_TAG
            )

            abMainActivityTvHeader.text = resources.getString(R.string.episodes)

            abMainActivityIcHome.vision(View.VISIBLE)

            abMainActivityIcBack.vision(View.GONE)
        }
    }
    private fun initNetworkChecker(){
        disposable.add(
        networkStatus.isOnline()
            .subscribeOn(Schedulers.single())
            .observeOn(mainFlow)
            .subscribe(
                {
                    if (!it) Toast.makeText(this, "connection lost", Toast.LENGTH_LONG).show()
                },
                {
                    Toast.makeText(this, "Unknown network state", Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}