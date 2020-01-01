package com.ajithvgiri.searchdialog

import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var searchableDialog: SearchableDialog
    var searchListItems: MutableList<SearchListItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..9) {
            val searchListItem = SearchListItem(i, "Country $i")
            searchListItems.add(searchListItem)
        }
        val countryNameTextView = findViewById<TextView>(R.id.countryNameTextView)
        val countryCodeTextView = findViewById<TextView>(R.id.countryCodeTextView)
        searchableDialog = SearchableDialog(this, searchListItems, getString(R.string.country))
        searchableDialog.setOnItemSelected { _, searchListItem ->
            countryCodeTextView.text = searchListItem.getId().toString()
            countryNameTextView.text = searchListItem.getTitle()
        }
        val countryTextInputEditText = findViewById<TextInputEditText>(R.id.countryTextInputEditText)
        countryTextInputEditText.setOnClickListener { searchableDialog.show() }


    }
}