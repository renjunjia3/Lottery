package com.quduo.lottery.ui.discover.entity;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * 发现
 * Created by scene on 2017/12/19.
 */

public class DiscoverInfo implements Serializable {
    @DrawableRes
    private int imageResId;
    private String title;
    private String content;

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
