package com.ajithvgiri.searchdialog;

/**
 * Created by ajithvgiri on 06/11/17.
 */

public class SearchListItem {
    int id;
    String title;

    public SearchListItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
