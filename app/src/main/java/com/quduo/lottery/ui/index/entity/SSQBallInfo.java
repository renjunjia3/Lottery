package com.quduo.lottery.ui.index.entity;

import java.io.Serializable;

/**
 * 双色球
 * Created by scene on 2017/12/20.
 */

public class SSQBallInfo implements Serializable {
    private String number;
    private int missTime;
    private boolean isCheck;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMissTime() {
        return missTime;
    }

    public void setMissTime(int missTime) {
        this.missTime = missTime;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
