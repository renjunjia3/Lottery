package com.quduo.lottery.ui.index.view;

import com.quduo.lottery.mvp.BaseView;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;

import java.util.List;

/**
 * 双色球
 * Created by scene on 2017/12/19.
 */

public interface ISSQView extends BaseView {
    List<SSQBallInfo> getRedList();

    List<SSQBallInfo> getBlueList();

    void setTotalNumber(String number);

    void setTotalPrice(String price);

    void notifyRedAdapter();

    void notifyBuleAdapter();

    int getTotalStake();

    void toConfirmPage();
}
