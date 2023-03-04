package com.molchanov.molchanovrickandmorty.ui.main.pagination

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molchanov.molchanovrickandmorty.databinding.PaginationRvItemCheckedBinding
import com.molchanov.molchanovrickandmorty.databinding.PaginationRvItemUncheckedBinding

class PaginationRVAdapter(
    private val callback: OnListItemClickListener
): RecyclerView.Adapter<PaginationBaseViewHolder>() {

    companion object{
        private const val PAGE_CHECKED = 1
        private const val PAGE_UNCHECKED = 0
    }

    private var oldActivePage = 1

    private var pageList: MutableList<Pair<Int, Boolean>> = mutableListOf(Pair(1, true))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaginationBaseViewHolder {

        return when(viewType){
            PAGE_UNCHECKED -> { PaginationUncheckViewHolder(
                PaginationRvItemUncheckedBinding.inflate(LayoutInflater.from(parent.context)).root
                )
            }
            PAGE_CHECKED -> { PaginationCheckViewHolder(
                    PaginationRvItemCheckedBinding.inflate(LayoutInflater.from(parent.context)).root
                )
            }
            else -> { PaginationCheckViewHolder(
                    PaginationRvItemCheckedBinding.inflate(LayoutInflater.from(parent.context)).root
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (pageList[position].second) PAGE_CHECKED
        else PAGE_UNCHECKED
    }

    override fun onBindViewHolder(holder: PaginationBaseViewHolder, position: Int) {
        holder.bind(pageList[position])

        holder.itemView.setOnClickListener {

            if (oldActivePage != (position + 1))

            oldActivePage = position + 1

            replaceData(pageList.size, position + 1)

            callback.onItemClick(pageList[position])
        }
    }

    fun replaceData(pageNum: Int) {
        createPageList(pageNum, oldActivePage).let {
            pageList = it
            notifyDataSetChanged()
        }
    }

    fun replaceData(pageNum: Int, activePage: Int) {
        createPageList(pageNum, activePage).let {
            pageList = it
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return pageList.size
    }

    interface OnListItemClickListener {
        fun onItemClick(data: Pair<Int, Boolean>)
    }

    /**
     * Функция создаёт лист из ппры (номер станицы, тип)
     * тип: ture - станица активна/ false - пассивна
     */
    private fun createPageList(pageNum: Int, activePage: Int) : MutableList<Pair<Int, Boolean>>{

        val bufList = mutableListOf<Pair<Int, Boolean>>()

        for (i in 0 until pageNum){
            bufList.add(Pair(i + 1,false))
        }

        if (activePage > 0) bufList[activePage - 1] = Pair(activePage, true)
        else bufList[0] = Pair(activePage, true)

        return bufList
    }
}