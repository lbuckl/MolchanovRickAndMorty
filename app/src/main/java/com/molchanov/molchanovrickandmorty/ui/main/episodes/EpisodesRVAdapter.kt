package com.molchanov.molchanovrickandmorty.ui.main.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.molchanovrickandmorty.databinding.FragmentCharactersEpisodeRvItemBinding

/**
 * Адаптер для отображения и взаимодействия персонажей
 */
class EpisodesRVAdapter: RecyclerView.Adapter<EpisodesBaseViewHolder>() {

    private var episodeList: List<String> = listOf("")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesBaseViewHolder {
        val binding = FragmentCharactersEpisodeRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return EpisodesDefaultViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: EpisodesBaseViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    fun replaceData(episodes: List<String>) {
        episodeList = episodes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return episodeList.size
    }
}