package com.example.mysqldemo;

import android.graphics.Bitmap;

import java.io.File;

public class CategoryInfo {


    private String title;
    private String image;

    public CategoryInfo(String title, String image) {

        this.title = title;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + "\n";
    }
}
