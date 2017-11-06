package com.ajithvgiri.searchdialog;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    SearchableDialog searchableDialog;
    List<SearchListItem> searchListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for (int i = 0; i < 10; i++) {
            SearchListItem searchListItem = new SearchListItem(i, "Country " + i);
            searchListItems.add(searchListItem);
        }


        final TextView countryNameTextView = findViewById(R.id.countryNameTextView);
        final TextView countryCodeTextView = findViewById(R.id.countryCodeTextView);

        searchableDialog = new SearchableDialog(this, searchListItems, getString(R.string.country));
        searchableDialog.setOnItemSelected(new OnSearchItemSelected() {
            @Override
            public void onClick(int position, SearchListItem searchListItem) {
                countryCodeTextView.setText(String.valueOf(searchListItem.getId()));
                countryNameTextView.setText(searchListItem.getTitle());
            }
        });
        TextInputEditText countryTextInputEditText = findViewById(R.id.countryTextInputEditText);
        countryTextInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchableDialog.show();
            }
        });
    }
}
