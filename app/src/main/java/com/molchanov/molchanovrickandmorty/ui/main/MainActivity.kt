package com.molchanov.molchanovrickandmorty.ui.main

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.molchanov.repository.remote.RepositoryRemoteImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
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
        naviGoToCharacterFragment()

        tets()
    }

    private fun tets(){
        RepositoryRemoteImpl().getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {
                it[1]
            },
            {
                Log.v("@@@", it.message.toString())
            }
        )
    }

    private fun initMenuListener() {

        binding.bnvMain.menu.let { menu ->
            Log.v("@@@" , "menu")

            binding.bnvMain.setOnItemSelectedListener { item ->

                Log.v("@@@" , "setOnItemSelectedListener")
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

}