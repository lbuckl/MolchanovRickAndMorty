package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.molchanov.domain.character.Character
import com.molchanov.molchanovrickandmorty.App
import com.molchanov.molchanovrickandmorty.R
import com.molchanov.molchanovrickandmorty.databinding.FragmentCharactersBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseFragment
import com.molchanov.molchanovrickandmorty.ui.base.ViewModelFactory
import com.molchanov.molchanovrickandmorty.ui.pagination.PaginationRVAdapter
import com.molchanov.molchanovrickandmorty.utils.loadImageFromUrl
import com.molchanov.molchanovrickandmorty.utils.vision
import javax.inject.Inject

/**
 * Фрагмент для взаимодействия со списком персонажей
 */
class CharactersFragment: BaseFragment<FragmentCharactersBinding>() {

    @Inject
    lateinit var vmFactory: ViewModelFactory

    companion object {
        val instance = CharactersFragment()
        const val FRAGMENT_TAG = "CharactersFragment_IdentificationTag"
    }

    private lateinit var viewModel: CharactersViewModel

    /**
     * Коллбэк от элементов reciclerView со списком персонажей
     */
    private val onRVItemClickListener = object : CharactersRVAdapter.OnListItemClickListener {
        override fun onItemClick(data: Character) {
        }
    }

    /**
     * Коллбэк от элементов reciclerView с пагинацией
     */
    private val onPagRVItemClickListener = object : PaginationRVAdapter.OnListItemClickListener {
        override fun onItemClick(data: Pair<Int, Boolean>) {
            binding.errorLayout.vision(View.GONE)

            viewModel.getData(data.first)
        }
    }

    private val rvAdapter = CharactersRVAdapter(onRVItemClickListener)

    private val pagRvAdapter = PaginationRVAdapter(onPagRVItemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.app.appComponent.inject(this)

        initRvAdapters()

        initViewModel()

        initButtons()

        return binding.root
    }

    override fun getViewBinding(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }

    private fun initRvAdapters(){
        binding.rvCharacters.adapter = rvAdapter

        binding.include.rvPagination.adapter = pagRvAdapter

        binding.rvCharacters.layoutManager = GridLayoutManager(this.context,2)
    }

    private fun initViewModel() {

        viewModel = ViewModelProvider(this, vmFactory)[CharactersViewModel::class.java]

        App.app.appComponent.inject(viewModel)

        viewModel.getMyLiveData().observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun initButtons(){
        binding.btnReload.setOnClickListener {

            binding.errorLayout.vision(View.GONE)

            viewModel.reloadData()
        }
    }

    private fun renderData(state: CharactersAppState){

        when(state){
            is CharactersAppState.Success -> {
                binding.errorLayout.vision(View.GONE)

                with(state.data){
                    rvAdapter.replaceData(this)
                    pagRvAdapter.replaceData(pageNum, pageActual)
                }
            }
            is CharactersAppState.Error -> {
                binding.errorLayout.vision(View.VISIBLE)

                Snackbar.make(binding.root,state.errorMsg,Snackbar.LENGTH_LONG).show()
            }
            is CharactersAppState.Loading ->{
                with(binding.ivLoading){
                    if (state.isLoading) {
                        vision(View.VISIBLE)
                        loadImageFromUrl(R.drawable.gif_rm_dance)
                    }
                    else{
                        vision(View.GONE)
                    }
                }
            }
        }
    }
}