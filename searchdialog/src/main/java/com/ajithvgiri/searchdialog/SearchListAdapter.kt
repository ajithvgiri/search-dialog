package com.ajithvgiri.searchdialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ajithvgiri on 06/11/17.
 */
class SearchListAdapter(context: Context, objects: ArrayList<SearchListItem>) :
    ArrayAdapter<SearchListItem>(
        context,
        R.layout.items_view_layout
    ) {
    var searchListItems: ArrayList<SearchListItem> = objects
    var suggestions: ArrayList<SearchListItem> = ArrayList()
    var filter = CustomFilter()

    override fun getCount(): Int {
        return searchListItems.size
    }

    override fun getItem(i: Int): SearchListItem {
        return searchListItems[i]
    }

    override fun getItemId(i: Int): Long {
        return searchListItems[i].id.toLong()
    }

    fun getPosition(id: Int): Int {
        var position = 0
        for (i in searchListItems.indices) {
            if (id == searchListItems[i].id) {
                position = i
            }
        }
        return position
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var inflateview = convertView
        if (inflateview == null) {
            inflateview = LayoutInflater.from(context).inflate(R.layout.items_view_layout, parent)
        }
        val tv = inflateview?.findViewById<View>(R.id.text1) as TextView
        tv.text = searchListItems[position].title
        return inflateview!!
    }



    override fun getFilter(): Filter {
        return filter
    }

    inner class CustomFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            suggestions.clear()
            constraint.let {
                for (i in searchListItems.indices) {
                    if (searchListItems[i].title.toLowerCase(Locale.ENGLISH)
                            .contains(constraint)
                    ) { // Compare item in original searchListItems if it contains constraints.
                        suggestions.add(searchListItems[i]) // If TRUE add item in Suggestions.
                    }
                }
            }
            val results =
                FilterResults() // Create new Filter Results and return this to publishResults;
            results.values = suggestions
            results.count = suggestions.size
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            if (results.count > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }
    }

}