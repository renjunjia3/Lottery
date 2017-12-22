package com.quduo.lottery.ui.index.view;

import com.quduo.lottery.mvp.BaseView;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;

import java.util.List;

/**
 * 时时彩
 * Created by scene on 2017/12/20.
 */

public interface IFC3DView extends BaseView {

    void showStar3Direct();

    void showStar3Combination3();

    void showStar3Combination6();

    void clearAllChoosedBall();

    List<SSQBallInfo> getList1();

    List<SSQBallInfo> getList2();

    List<SSQBallInfo> getList3();

    void showAllStakeAndPrice(int totalNumber);

    void notifyAllAdapter();

    int getCurrentPlayWayPosition();

    int getTotalStake();

    void toConfirmBetPage();
}
