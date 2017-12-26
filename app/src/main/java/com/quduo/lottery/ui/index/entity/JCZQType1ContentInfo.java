package com.quduo.lottery.ui.index.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quduo.lottery.ui.index.adapter.jczq.JCZQType1Adapter;

/**
 * Created by scene on 2017/12/26.
 */

public class JCZQType1ContentInfo implements MultiItemEntity {
    private String title;
    private boolean showHistory;

    public JCZQType1ContentInfo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowHistory() {
        return showHistory;
    }

    public void setShowHistory(boolean showHistory) {
        this.showHistory = showHistory;
    }

    @Override
    public int getItemType() {
        return JCZQType1Adapter.TYPE_LEVEL_1;
    }
}
