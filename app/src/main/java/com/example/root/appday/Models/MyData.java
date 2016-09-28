package com.example.root.appday.Models;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by root on 20/09/2016.
 */
public class MyData {

    private String mImageView;
    private String mName;
    private String mContent;


    public MyData(String mImageView, String mName, String mContent) {
        this.mImageView = mImageView;
        this.mName = mName;
        this.mContent = mContent;
    }

    public String getmImageView() {
        return mImageView;
    }

    public void setmImageView(String mImageView) {
        this.mImageView = mImageView;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}