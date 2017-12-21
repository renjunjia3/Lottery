package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.SSCModel;
import com.quduo.lottery.ui.index.view.ISSCView;

/**
 * 时时彩
 * Created by scene on 2017/12/20.
 */

public class SSCPresenter extends BasePresenter<ISSCView> {
    private SSCModel model;

    public SSCPresenter(ISSCView view) {
        this.mView = view;
        model = new SSCModel();
    }

    //机选
    public void clickMachine() {

    }

    //改变玩法布局
    public void changeLayoutView(int position) {
        try {
            switch (position) {
                case 0:
                    mView.showStar5Direct();
                    break;
                case 1:
                    mView.showStar5Combination();
                    break;
                case 2:
                    mView.showBigSmallSingleDouble();
                    break;
                case 3:
                    mView.showStar3Direct();
                    break;
                case 4:
                    mView.showStar3Combination3();
                    break;
                case 5:
                    mView.showStar3Combination6();
                    break;
                case 6:
                    mView.showStar2Direct();
                    break;
                case 7:
                    mView.showStar2Combination();
                    break;
                case 8:
                    mView.showStar1Direct();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
