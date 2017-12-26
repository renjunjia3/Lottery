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
}
