package com.quduo.lottery.ui.index.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quduo.lottery.ui.index.adapter.JCLQMatchResultAdapter;

/**
 * Created by scene on 2017/12/26.
 */

public class JCLQMatchResultHeaderInfo extends AbstractExpandableItem<JCLQMatchResultContentInfo> implements MultiItemEntity {
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int getLevel() {
        return JCLQMatchResultAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getItemType() {
        return JCLQMatchResultAdapter.TYPE_LEVEL_0;
    }
}
