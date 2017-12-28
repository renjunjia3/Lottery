package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.JCLQModel;
import com.quduo.lottery.ui.index.view.IJCLQView;

/**
 * 竞彩篮球
 * Created by scene on 2017/12/26.
 */

public class JCLQPresenter extends BasePresenter<IJCLQView> {
    private JCLQModel model;

    public JCLQPresenter(IJCLQView view) {
        this.mView = view;
        this.model = new JCLQModel();
    }

    public void changeLayoutView() {
        try {
            switch (mView.getPlayWayPosition()) {
                case 0:
                    mView.showPlayWayType1();
                    break;
                case 1:
                    mView.showPlayWayType2();
                    break;
                case 2:
                    mView.showPlayWayType3();
                    break;
                case 3:
                    mView.showPlayWayType4();
                    break;
                case 4:
                    mView.showPlayWayType5();
                    break;
                case 5:
                    mView.showPlayWayType6();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
