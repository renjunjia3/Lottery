package com.quduo.lottery.ui.mine.entity;

import java.io.Serializable;

/**
 * 投注记录
 * Created by scene on 2018/1/5.
 */

public class BettingRecordInfo implements Serializable {
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
