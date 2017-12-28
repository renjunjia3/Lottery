package com.quduo.lottery.ui.index.view;

import com.quduo.lottery.mvp.BaseView;

/**
 * 竞彩篮球
 * Created by scene on 2017/12/26.
 */

public interface IJCLQView extends BaseView {
    int getPlayWayPosition();

    void showPlayWayType1();

    void showPlayWayType2();

    void showPlayWayType3();

    void showPlayWayType4();

    void showPlayWayType5();

    void showPlayWayType6();
}
