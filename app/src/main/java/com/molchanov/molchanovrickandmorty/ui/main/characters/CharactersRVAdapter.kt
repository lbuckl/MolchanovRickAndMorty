package com.molchanov.molchanovrickandmorty.ui.main.characters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.domain.character.*
import com.molchanov.molchanovrickandmorty.databinding.FragmentCharactersRvItemBinding

class CharactersRVAdapter(
    private val callback: OnListItemClickListener
): RecyclerView.Adapter<CharactersBaseViewHolder>() {

    private var characterList: List<Character> = listOf()

    interface OnListItemClickListener {
        fun onItemClick(data: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersBaseViewHolder {
        val binding = FragmentCharactersRvItemBinding.inflate(LayoutInflater.from(parent.context))
        return CharactersDefaultViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CharactersBaseViewHolder, position: Int) {
        holder.bind(characterList[position])

        holder.itemView.setOnClickListener {
            callback.onItemClick(characterList[position])
        }
    }

    fun replaceData(newOfficeList: List<Character>) {
        characterList = newOfficeList
        Log.v("@@@", characterList.size.toString())
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}