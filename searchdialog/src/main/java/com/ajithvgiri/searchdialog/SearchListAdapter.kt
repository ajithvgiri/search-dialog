package com.ajithvgiri.searchdialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import java.util.*

/**
 * Created by ajithvgiri on 06/11/17.
 */
class SearchListAdapter(context: Context, resource: Int, textViewResourceId: Int, objects: List<SearchListItem>) : ArrayAdapter<Any?>(context, resource, textViewResourceId, objects) {
    var searchListItems: List<SearchListItem> = objects
    var suggestions: MutableList<SearchListItem> = ArrayList()
    var filter = CustomFilter()
    private val textViewResourceID: Int = textViewResourceId

    override fun getCount(): Int {
        return searchListItems.size
    }

    override fun getItem(i: Int): Any? {
        return searchListItems[i].title
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

    override fun getView(i: Int, inflateview: View, viewGroup: ViewGroup): View {
        val tv = inflateview.findViewById<View>(textViewResourceID) as TextView
        tv.text = searchListItems[i].title
        return inflateview
    }

    override fun getFilter(): Filter {
        return filter
    }

    inner class CustomFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            suggestions.clear()
            constraint.let {
                for (i in searchListItems.indices) {
                    if (searchListItems[i].title.toLowerCase(Locale.ENGLISH).contains(constraint)) { // Compare item in original searchListItems if it contains constraints.
                        suggestions.add(searchListItems[i]) // If TRUE add item in Suggestions.
                    }
                }
            }
            val results = FilterResults() // Create new Filter Results and return this to publishResults;
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