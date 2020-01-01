package com.ajithvgiri.searchdialogdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajithvgiri.searchdialog.OnSearchItemSelected
import com.ajithvgiri.searchdialog.SearchListItem
import com.ajithvgiri.searchdialog.SearchableDialog
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.country_details.*


class MainActivity : AppCompatActivity(), OnSearchItemSelected {


    lateinit var searchableDialog: SearchableDialog
    var searchListItems: ArrayList<SearchListItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..9) {
            val searchListItem = SearchListItem(i, "Country $i")
            searchListItems.add(searchListItem)
        }

        searchableDialog = SearchableDialog(this, searchListItems, getString(R.string.country))

        val countryTextInputEditText = findViewById<TextInputEditText>(R.id.countryTextInputEditText)
        countryTextInputEditText.setOnClickListener { searchableDialog.show() }

    }

    override fun onClick(position: Int, searchListItem: SearchListItem) {
        countryCodeTextView.text = searchListItem.id.toString()
        countryNameTextView.text = searchListItem.title
    }
}