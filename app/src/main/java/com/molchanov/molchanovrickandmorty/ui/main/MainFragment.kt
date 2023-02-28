package com.molchanov.molchanovrickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanovrickandmorty.databinding.FragmentMainBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseFragment

class MainFragment(): BaseFragment<FragmentMainBinding>() {

    companion object {
        val instance = MainFragment()
        const val FRAGMENT_TAG = "MainFragmentIdentificationTag"
    }

    override val binding: FragmentMainBinding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
}