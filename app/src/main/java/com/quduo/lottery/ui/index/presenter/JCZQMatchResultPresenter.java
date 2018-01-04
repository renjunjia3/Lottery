package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.JCLQMatchResultModel;
import com.quduo.lottery.ui.index.model.JCZQMatchResultModel;
import com.quduo.lottery.ui.index.view.IJCLQMatchResultView;
import com.quduo.lottery.ui.index.view.IJCZQMatchResultView;

/**
 * 足球比赛结果
 * Created by scene on 2018/1/3.
 */

public class JCZQMatchResultPresenter extends BasePresenter<IJCZQMatchResultView> {
    private JCZQMatchResultModel model;

    public JCZQMatchResultPresenter(IJCZQMatchResultView view) {
        this.mView = view;
        this.model = new JCZQMatchResultModel();
    }
}
