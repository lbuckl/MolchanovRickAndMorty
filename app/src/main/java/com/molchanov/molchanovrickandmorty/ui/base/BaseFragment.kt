package com.molchanov.molchanovrickandmorty.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private var _binding: T? = null

    protected val binding: T
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = getViewBinding()
    }

    abstract fun getViewBinding(): T

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}