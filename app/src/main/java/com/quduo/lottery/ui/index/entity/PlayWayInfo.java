package com.quduo.lottery.ui.index.entity;

import java.io.Serializable;

/**
 * 时时彩玩法
 * Created by scene on 2017/12/20.
 */

public class PlayWayInfo implements Serializable {
    private String playWayName;
    private boolean isJiajiang;
    private boolean isChoosed;

    public PlayWayInfo() {
    }

    public PlayWayInfo(String playWayName, boolean isJiajiang, boolean isChoosed) {
        this.playWayName = playWayName;
        this.isJiajiang = isJiajiang;
        this.isChoosed = isChoosed;
    }

    public String getPlayWayName() {
        return playWayName;
    }

    public void setPlayWayName(String playWayName) {
        this.playWayName = playWayName;
    }

    public boolean isJiajiang() {
        return isJiajiang;
    }

    public void setJiajiang(boolean jiajiang) {
        isJiajiang = jiajiang;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }
}
