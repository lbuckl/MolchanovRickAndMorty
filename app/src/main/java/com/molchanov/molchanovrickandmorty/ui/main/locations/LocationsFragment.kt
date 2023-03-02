package com.molchanov.molchanovrickandmorty.ui.main.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanovrickandmorty.databinding.FragmentLocationsBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseFragment

class LocationsFragment(): BaseFragment<FragmentLocationsBinding>() {

    companion object {
        val instance = LocationsFragment()
        const val FRAGMENT_TAG = "LocationsFragment_IdentificationTag"
    }

    override val binding: FragmentLocationsBinding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }
}