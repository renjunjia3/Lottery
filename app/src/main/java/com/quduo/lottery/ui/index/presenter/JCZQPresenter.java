package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.JCZQModel;
import com.quduo.lottery.ui.index.view.IJCZQView;

/**
 * 竞彩足球
 * Created by scene on 2017/12/26.
 */

public class JCZQPresenter extends BasePresenter<IJCZQView> {
    private JCZQModel model;

    public JCZQPresenter(IJCZQView view) {
        this.mView = view;
        model = new JCZQModel();
    }

    public void changeLayoutView() {
        try {
            int position = mView.getPlayWayPosition();
            switch (position) {
                case 0:
                    mView.showPlayWay1();
                    break;
                case 1:
                    mView.showPlayWay2();
                    break;
                case 2:
                    mView.showPlayWay3();
                    break;
                case 3:
                    mView.showPlayWay4();
                    break;
                case 4:
                    mView.showPlayWay5();
                    break;
                case 5:
                    mView.showPlayWay6();
                    break;
                case 6:
                    mView.showPlayWay7();
                    break;
                case 7:
                    mView.showPlayWay8();
                    break;
                case 8:
                    mView.showPlayWay9();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
