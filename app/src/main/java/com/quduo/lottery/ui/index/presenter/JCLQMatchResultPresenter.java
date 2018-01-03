package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.JCLQMatchResultModel;
import com.quduo.lottery.ui.index.view.IJCLQMatchResultView;

/**
 * 篮球比赛结果
 * Created by scene on 2018/1/3.
 */

public class JCLQMatchResultPresenter extends BasePresenter<IJCLQMatchResultView> {
    private JCLQMatchResultModel model;

    public JCLQMatchResultPresenter(IJCLQMatchResultView view) {
        this.mView = view;
        this.model = new JCLQMatchResultModel();
    }
}
