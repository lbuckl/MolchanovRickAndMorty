package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.molchanov.domain.character.Character
import com.molchanov.molchanovrickandmorty.App
import com.molchanov.molchanovrickandmorty.R
import com.molchanov.molchanovrickandmorty.databinding.FragmentCharactersBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseFragment
import com.molchanov.molchanovrickandmorty.ui.pagination.PaginationRVAdapter
import com.molchanov.molchanovrickandmorty.ui.router.IRouter
import com.molchanov.molchanovrickandmorty.utils.vision
import javax.inject.Inject

/**
 * Фрагмент для взаимодействия со списком персонажей
 */
class CharactersFragment: BaseFragment<FragmentCharactersBinding>() {

    companion object {
        val instance = CharactersFragment()
        const val FRAGMENT_TAG = "CharactersFragment_IdentificationTag"
    }

    @Inject
    lateinit var router: IRouter

    private lateinit var viewModel: CharactersViewModel

    private var localLoading = false

    /**
     * Коллбэк от элементов recyclerView со списком персонажей
     */
    private val onRVItemClickListener = object : CharactersRVAdapter.OnListItemClickListener {
        override fun onItemClick(data: Character) {
            viewModel.getCharacterInfo(data)

            binding.abCharacterIcFilter.vision(View.GONE)
            binding.abCharacterIcSearch.vision(View.GONE)
        }
    }

    /**
     * Коллбэк от элементов recyclerView с пагинацией
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

        initLoading()

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

        viewModel.getMyLiveData().value?.let {
            Log.v("@@@", it.toString())

            renderData(it)
        }
    }

    private fun initButtons(){

        binding.btnReload.setOnClickListener {

            binding.errorLayout.vision(View.GONE)

            viewModel.reloadData()
        }

        binding.abMainActivityIcBack.setOnClickListener {
            router.deleteFragment(
                childFragmentManager,
                binding.flCharacterContainer.id,
                CharacterDetailsFragment.instance,
                CharacterDetailsFragment.FRAGMENT_TAG
            )

            viewModel.reloadData()

            with(binding){
                flCharacterContainer.vision(View.GONE)

                abMainActivityIcBack.vision(View.GONE)

                abCharacterIcFilter.vision(View.VISIBLE)

                abCharacterIcSearch.vision(View.VISIBLE)
            }
        }
    }

    private fun initLoading(){
        with(binding.ivLoading){
            Glide.with(this)
                .load(R.drawable.gif_rm_dance)
                .placeholder(R.drawable.ic_no_photo_vector)
                .into(this)
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
            is CharactersAppState.SuccessCharacter -> {
                val bundle = Bundle()
                bundle.putParcelable(
                    CharacterDetailsFragment.FRAGMENT_MESSAGE_TAG,
                    state.data
                )

                router.replaceFragmentWithMessage(
                    childFragmentManager,
                    binding.flCharacterContainer.id,
                    CharacterDetailsFragment.instance,
                    CharacterDetailsFragment.FRAGMENT_TAG,
                    bundle
                )

                binding.flCharacterContainer.vision(View.VISIBLE)

                binding.abMainActivityIcBack.vision(View.VISIBLE)
            }
            is CharactersAppState.Error -> {
                binding.errorLayout.vision(View.VISIBLE)

                Snackbar.make(binding.root,state.errorMsg,Snackbar.LENGTH_LONG).show()
            }
            is CharactersAppState.ErrorCharacter -> {
                Snackbar.make(binding.rvCharacters,state.errorMsg,Snackbar.LENGTH_LONG).show()
            }
            is CharactersAppState.Loading ->{
                with(binding.ivLoading){
                    if (state.isLoading) {

                        localLoading = true

                        loadingShowDelay()
                    }
                    else{
                        localLoading = true

                        vision(View.GONE)
                    }
                }
            }
        }
    }

    private fun loadingShowDelay(){
        Thread {
            Thread.sleep(500)

            if (localLoading) {
                Handler(Looper.myLooper()!!).post {
                    binding.ivLoading.vision(View.VISIBLE)
                }
            }
        }
    }
}