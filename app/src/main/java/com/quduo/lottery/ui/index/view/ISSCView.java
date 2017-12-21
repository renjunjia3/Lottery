package com.quduo.lottery.ui.index.view;

import com.quduo.lottery.mvp.BaseView;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;

import java.util.List;

/**
 * 时时彩
 * Created by scene on 2017/12/20.
 */

public interface ISSCView extends BaseView {
    void showStar5Direct();

    void showStar5Combination();

    void showBigSmallSingleDouble();

    void showStar3Direct();

    void showStar3Combination3();

    void showStar3Combination6();

    void showStar2Direct();

    void showStar2Combination();

    void showStar1Direct();

    void clearAllChoosedBall();

    List<SSQBallInfo> getList1();

    List<SSQBallInfo> getList2();

    List<SSQBallInfo> getList3();

    List<SSQBallInfo> getList4();

    List<SSQBallInfo> getList5();

    List<SSQBallInfo> getListBigSmallSingleDouble1();

    List<SSQBallInfo> getListBigSmallSingleDouble2();

    void showAllStakeAndPrice(int totalNumber);

    void notifyAllAdapter();

    int getCurrentPlayWayPosition();

    int getTotalStake();

    void toConfirmBetPage();
}
