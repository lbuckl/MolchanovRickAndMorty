package com.molchanov.molchanovrickandmorty.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.molchanov.molchanovrickandmorty.App
import com.molchanov.molchanovrickandmorty.R
import com.molchanov.molchanovrickandmorty.data.networkstatus.INetworkStatus
import com.molchanov.molchanovrickandmorty.databinding.ActivityMainBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseActivity
import com.molchanov.molchanovrickandmorty.ui.base.BaseFragment
import com.molchanov.molchanovrickandmorty.ui.main.characters.CharactersFragment
import com.molchanov.molchanovrickandmorty.ui.main.episodes.EpisodesFragment
import com.molchanov.molchanovrickandmorty.ui.main.locations.LocationsFragment
import com.molchanov.molchanovrickandmorty.ui.router.IRouter
import com.molchanov.molchanovrickandmorty.utils.vision
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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

        navigateTo(
            CharactersFragment.instance,
            CharactersFragment.FRAGMENT_TAG
        )
    }

    private fun initMenuListener() {

        binding.bnvMain.menu.let { menu ->

            binding.bnvMain.setOnItemSelectedListener { item ->

                when (item) {
                    menu.findItem(R.id.bv_item_characters) -> {

                        navigateTo(
                            CharactersFragment.instance,
                            CharactersFragment.FRAGMENT_TAG
                        )
                    }
                    menu.findItem(R.id.bv_item_locations) -> {

                        navigateTo(
                            LocationsFragment.instance,
                            LocationsFragment.FRAGMENT_TAG
                        )
                    }
                    menu.findItem(R.id.bv_item_episodes) -> {

                        navigateTo(
                            EpisodesFragment.instance,
                            EpisodesFragment.FRAGMENT_TAG
                        )
                    }
                }

                return@setOnItemSelectedListener true
            }
        }
    }

    private fun navigateTo(fragment: Fragment, tag: String){

        with(binding){
            router.addFragment(
                supportFragmentManager,
                container.id,
                fragment,
                tag)
        }
    }

    private fun initNetworkChecker(){
        disposable.add(
        networkStatus.isOnline()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
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