package com.ajithvgiri.searchdialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_view_layout.view.*


class SearchAdapter(var onSearchItemSelected: OnSearchItemSelected, private var list: ArrayList<SearchListItem>) : RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_view_layout, parent, false)
        return SearchAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        val searchListItem = list[position]
        holder.bind(searchListItem, onSearchItemSelected, position)
    }

    public class SearchAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(searchListItem: SearchListItem, onSearchItemSelected: OnSearchItemSelected, position: Int) {
            itemView.text1.text = searchListItem.title
            itemView.text1.setOnClickListener {
                onSearchItemSelected.onClick(position = position, searchListItem = searchListItem)
            }
        }
    }
}