package com.quduo.lottery.ui.index.view;

import com.quduo.lottery.mvp.BaseView;

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
}
