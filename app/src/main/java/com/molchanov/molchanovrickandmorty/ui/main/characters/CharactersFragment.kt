package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.molchanov.molchanovrickandmorty.databinding.FragmentCharactersBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseFragment
import com.molchanov.domain.character.*
import com.molchanov.molchanovrickandmorty.App
import com.molchanov.molchanovrickandmorty.ui.base.ViewModelFactory
import javax.inject.Inject

class CharactersFragment: BaseFragment<FragmentCharactersBinding>() {

    @Inject lateinit var vmFactory: ViewModelFactory

    companion object {
        val instance = CharactersFragment()
        const val FRAGMENT_TAG = "CharactersFragment_IdentificationTag"
    }

    private lateinit var viewModel: CharactersViewModel

    private val onRVItemClickListener = object : CharactersRVAdapter.OnListItemClickListener {
        override fun onItemClick(data: Character) {
        }
    }

    private val rvAdapter = CharactersRVAdapter(onRVItemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.app.appComponent.inject(this)

        binding.rvCharacters.adapter = rvAdapter

        binding.rvCharacters.layoutManager = GridLayoutManager(this.context,2)

        initViewModel()

        return binding.root
    }

    override fun getViewBinding(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }

    private fun initViewModel() {

        viewModel = ViewModelProvider(this, vmFactory)[CharactersViewModel::class.java]

        App.app.appComponent.inject(viewModel)

        viewModel.getMyLiveData().observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun renderData(state: CharactersAppState){
        when(state){
            is CharactersAppState.Success -> {
               rvAdapter.replaceData(state.data)
                Log.v("@@@", "Success")
            }
            is CharactersAppState.Error -> {

            }
        }
    }
}