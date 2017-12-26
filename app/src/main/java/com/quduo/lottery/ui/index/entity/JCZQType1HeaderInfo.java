package com.quduo.lottery.ui.index.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quduo.lottery.ui.index.adapter.jczq.JCZQType1Adapter;

/**
 * Created by scene on 2017/12/26.
 */

public class JCZQType1HeaderInfo extends AbstractExpandableItem<JCZQType1ContentInfo> implements MultiItemEntity {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getLevel() {
        return JCZQType1Adapter.TYPE_LEVEL_0;
    }

    @Override
    public int getItemType() {
        return JCZQType1Adapter.TYPE_LEVEL_0;
    }
}
