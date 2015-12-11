package com.hisham.design.model;

/**
 * Created by faisal on 12/7/2015.
 */
public class RecyclerViewModel {
    private String title;
    private int thumbnail;

    public RecyclerViewModel(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public int getThumbnail() {
        return thumbnail;
    }

}
