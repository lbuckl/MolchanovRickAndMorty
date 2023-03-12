package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.domain.character.Character
import com.molchanov.molchanovrickandmorty.databinding.FragmentCharactersDetailsBinding
import com.molchanov.molchanovrickandmorty.ui.base.BaseFragment
import com.molchanov.molchanovrickandmorty.utils.loadImageFromUrl

class CharacterDetailsFragment: BaseFragment<FragmentCharactersDetailsBinding>() {

    companion object {
        val instance = CharacterDetailsFragment()
        const val FRAGMENT_TAG = "CharacterDetailsFragment_IdentificationTag"
        const val FRAGMENT_MESSAGE_TAG = "CharacterDetailsFragmentMessageTag"
    }

    override fun getViewBinding(): FragmentCharactersDetailsBinding {
        return FragmentCharactersDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initParentData()

        return binding.root
    }

    private fun initParentData(){

        arguments?.getParcelable<Character>(FRAGMENT_MESSAGE_TAG).let {
            if (it != null){
                with(binding){
                    this.tvCharacterName.text = "Name: ${it.name}"
                    this.tvCharacterSpec.text = "Spec: ${it.spec}"
                    this.tvCharacterGender.text = "Gender: ${it.gender}"
                    this.tvCharacterStatus.text = "Status: ${it.status}"
                    this.ivCharacterDetails.loadImageFromUrl(it.imgUrl)
                }
            }
            else {
                //TODO (Error msg)
            }
        }
    }
}