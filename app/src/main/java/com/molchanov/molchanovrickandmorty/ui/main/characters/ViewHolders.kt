package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.domain.character.*
import com.molchanov.molchanovrickandmorty.databinding.FragmentCharactersRvItemBinding
import com.molchanov.molchanovrickandmorty.utils.loadImageFromUrl

abstract class CharactersBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: Character)
}

class CharactersDefaultViewHolder(view: View) : CharactersBaseViewHolder(view) {

    override fun bind(data: Character) {
        FragmentCharactersRvItemBinding.bind(itemView).also {
            it.ivCharacterIcon.loadImageFromUrl(data.imgUrl)
            it.tvCharacterName.text = data.name
            it.tvCharacterSpec.text = data.spec
            it.tvCharacterGender.text = data.gender
            it.tvCharacterStatus.text = data.status
        }
    }
}