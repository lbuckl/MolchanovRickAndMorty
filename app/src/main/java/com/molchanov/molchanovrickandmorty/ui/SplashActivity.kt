package com.molchanov.molchanovrickandmorty.ui

import android.annotation.SuppressLint
import com.molchanov.molchanovrickandmorty.databinding.ActivitySplashBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun addMainFragment() {
        //TODO nothing
    }
}