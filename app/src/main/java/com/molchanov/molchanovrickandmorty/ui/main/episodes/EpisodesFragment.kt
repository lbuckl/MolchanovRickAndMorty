package com.molchanov.molchanovrickandmorty.ui.main.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanovrickandmorty.databinding.FragmentEpisodesBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseFragment

class EpisodesFragment(): BaseFragment<FragmentEpisodesBinding>() {

    companion object {
        val instance = EpisodesFragment()
        const val FRAGMENT_TAG = "EpisodesFragment_IdentificationTag"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun getViewBinding(): FragmentEpisodesBinding {
        return FragmentEpisodesBinding.inflate(layoutInflater)
    }
}