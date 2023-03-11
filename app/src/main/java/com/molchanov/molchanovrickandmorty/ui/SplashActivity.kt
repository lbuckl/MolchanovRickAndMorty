package com.molchanov.molchanovrickandmorty.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import com.molchanov.molchanovrickandmorty.R
import com.molchanov.molchanovrickandmorty.databinding.ActivitySplashBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseActivity
import com.molchanov.molchanovrickandmorty.ui.main.MainActivity

/**
 * Кастомный SplashScreen c логикой отработки в зависимости от версии Android
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity<ActivitySplashBinding>() {

    private val splashDelay = 2000L

    private val handler = Handler(Looper.myLooper()!!)

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MolchanovSplash)
        super.onCreate(savedInstanceState)
    }

    override fun addMainFragment() {
        val newActivityIntent = Intent(this, MainActivity::class.java)
        /**
         * Если верися API начиная от 32 (Android 12) - используется дефолтный сплешскрин
         * если ниже - кастомный
         */

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            showSplashAndroidS(newActivityIntent)
        } else {
            showSplashAndroidR(newActivityIntent)
        }
    }

    private fun showSplashAndroidR(intent: Intent){
        val runnable = Runnable {
            startActivity(intent)
            finish()
        }

        Thread{
            handler.postDelayed(runnable,splashDelay)
        }.start()
    }

    private fun showSplashAndroidS(intent: Intent){
        var isHideSplashScreen = false

        val runnable = Runnable {
            isHideSplashScreen = true
        }

        Thread{
            handler.postDelayed(runnable,splashDelay)
        }.start()

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        startActivity(intent)
                        finish()
                        true
                    } else {
                        false
                    }
                }
            })
    }
}