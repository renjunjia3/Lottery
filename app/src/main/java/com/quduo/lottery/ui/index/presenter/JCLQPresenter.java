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
}
