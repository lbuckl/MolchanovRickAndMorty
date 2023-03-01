package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanovrickandmorty.databinding.FragmentCharactersBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseFragment

class CharactersFragment(): BaseFragment<FragmentCharactersBinding>() {

    companion object {
        val instance = CharactersFragment()
        const val FRAGMENT_TAG = "CharactersFragment_IdentificationTag"
    }

    override val binding: FragmentCharactersBinding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        return binding.root
    }
}