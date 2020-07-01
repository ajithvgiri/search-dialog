package com.ajithvgiri.searchdialog

import android.app.Activity
import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by ajithvgiri on 06/11/17.
 */
public class SearchableDialog {
    var searchListItems: ArrayList<SearchListItem>
    var activity: Activity
    private var dialogTitle: String = ""
    private lateinit var onSearchItemSelected: OnSearchItemSelected
    private lateinit var alertDialog: AlertDialog
    private var position = 0
    var style = 0
    lateinit var adapter: SearchAdapter
    lateinit var recyclerView: RecyclerView

    constructor(activity: Activity, searchListItems: ArrayList<SearchListItem>, dialogTitle: String) {
        this.searchListItems = searchListItems
        this.activity = activity
        this.dialogTitle = dialogTitle
    }

    constructor(activity: Activity, searchListItems: ArrayList<SearchListItem>, dialogTitle: String, style: Int) {
        this.searchListItems = searchListItems
        this.activity = activity
        this.dialogTitle = dialogTitle
        this.style = style
    }

    /***
     *
     * @param searchItemSelected
     * return selected position & item
     */
    public fun setOnItemSelected(searchItemSelected: OnSearchItemSelected) {
        onSearchItemSelected = searchItemSelected
    }

    /***
     *
     * show the seachable dialog
     */
    fun show() {
        val adb = AlertDialog.Builder(activity)
        val view = activity.layoutInflater.inflate(R.layout.search_dialog_layout, null)
        val rippleViewClose = view.findViewById<View>(R.id.close) as TextView
        val title = view.findViewById<View>(R.id.spinerTitle) as TextView
        title.text = dialogTitle
        recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        val searchBox = view.findViewById<View>(R.id.searchBox) as EditText
        adapter = SearchAdapter(onSearchItemSelected,searchListItems)
        recyclerView.adapter = adapter
        adb.setView(view)
        alertDialog = adb.create()
        alertDialog.window?.attributes?.windowAnimations = style //R.style.DialogAnimations_SmileWindow;
        alertDialog.setCancelable(false)

        searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                val filteredValues = ArrayList<SearchListItem>()
                for (i in searchListItems.indices) {
                    val item = searchListItems[i]
                    if (item.title.toLowerCase().trim { it <= ' ' }.contains(searchBox.text.toString().toLowerCase().trim { it <= ' ' })) {
                        filteredValues.add(item)
                    }
                }
                adapter = SearchAdapter(onSearchItemSelected,filteredValues)
                recyclerView.adapter = adapter
            }
        })
        rippleViewClose.setOnClickListener { alertDialog.dismiss() }
        alertDialog.show()
    }

    /***
     *
     * clear the list
     */
    fun clear() {
        searchListItems.clear()
    }

    /***
     *
     * refresh the adapter (notifyDataSetChanged)
     */
    fun refresh() {
        adapter.notifyDataSetChanged()
    }

    /***
     *
     * dismiss the dialog
     */
    fun dismiss(){
        alertDialog.dismiss()
    }

    companion object {
        private const val TAG = "SearchableDialog"
    }
}