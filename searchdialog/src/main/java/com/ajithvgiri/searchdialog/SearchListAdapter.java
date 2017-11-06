package com.ajithvgiri.searchdialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajithvgiri on 06/11/17.
 */

public class SearchListAdapter extends ArrayAdapter {

    public static String TAG = "SearchListAdapter";
    public List<SearchListItem> searchListItems;
    List<SearchListItem> suggestions = new ArrayList<>();
    CustomFilter filter = new CustomFilter();
    LayoutInflater inflater;
    private int textviewResourceID;


    public SearchListAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
        this.searchListItems = objects;
        this.textviewResourceID = textViewResourceId;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return searchListItems.size();
    }

    @Override
    public Object getItem(int i) {
        return searchListItems.get(i).getTitle();
    }

    @Override
    public long getItemId(int i) {
        return searchListItems.get(i).getId();
    }


    public int getposition(int id) {
        int position = 0;
        for (int i = 0; i < searchListItems.size(); i++) {
            if (id == searchListItems.get(i).getId()) {
                position = i;
            }
        }
        return position;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflateview = view;
        if (inflateview == null) {
            inflateview = inflater
                    .inflate(R.layout.items_view_layout, null);

        }
        TextView tv = (TextView) inflateview.findViewById(textviewResourceID);
        tv.setText(searchListItems.get(i).getTitle());
        return inflateview;
    }


    @Override
    public Filter getFilter() {
        return filter;
    }


    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            suggestions.clear();
            if (searchListItems != null && constraint != null) { // Check if the Original List and Constraint aren't null.
                for (int i = 0; i < searchListItems.size(); i++) {
                    if (searchListItems.get(i).getTitle().toLowerCase().contains(constraint)) { // Compare item in original searchListItems if it contains constraints.
                        suggestions.add(searchListItems.get(i)); // If TRUE add item in Suggestions.
                    }
                }
            }
            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

}
