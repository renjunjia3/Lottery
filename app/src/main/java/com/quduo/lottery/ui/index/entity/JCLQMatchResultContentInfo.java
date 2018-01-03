package com.quduo.lottery.ui.index.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quduo.lottery.ui.index.adapter.JCLQMatchResultAdapter;

/**
 * Created by scene on 2017/12/26.
 */

public class JCLQMatchResultContentInfo implements MultiItemEntity {
    private String title;

    public JCLQMatchResultContentInfo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return JCLQMatchResultAdapter.TYPE_LEVEL_1;
    }
}
