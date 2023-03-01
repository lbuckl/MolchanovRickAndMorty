package com.molchanov.molchanovrickandmorty.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import com.molchanov.molchanovrickandmorty.databinding.ActivitySplashBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseActivity
import com.molchanov.molchanovrickandmorty.ui.main.MainActivity

/**
 * Кастомный SplashScreen c логикой отработки в зависимости от версии Android
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun addMainFragment() {
        val newActivityIntent = Intent(this, MainActivity::class.java)
        /**
         * Если верися API начиная от 31 (Android 12) - используется дефолтный сплешскрин
         * если ниже - кастомный
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            startActivity(newActivityIntent)
        } else {
            showSplash(newActivityIntent)
        }
    }

    private fun showSplash(intent: Intent){
        val handler = Handler(Looper.myLooper()!!)

        val runnable = Runnable {
            startActivity(intent)
        }

        Thread{
            Thread.sleep(2000)
            handler.post(runnable)
        }.start()
    }
}