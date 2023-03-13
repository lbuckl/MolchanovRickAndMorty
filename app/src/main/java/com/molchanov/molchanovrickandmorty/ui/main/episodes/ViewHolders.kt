package com.molchanov.molchanovrickandmorty.ui.main.episodes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.molchanovrickandmorty.databinding.FragmentCharactersEpisodeRvItemBinding

abstract class EpisodesBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: String)
}

class EpisodesDefaultViewHolder(view: View) : EpisodesBaseViewHolder(view) {

    override fun bind(data: String) {
        FragmentCharactersEpisodeRvItemBinding.bind(itemView).also {
            it.tvCharacterEpisode.text = data
        }
    }
}