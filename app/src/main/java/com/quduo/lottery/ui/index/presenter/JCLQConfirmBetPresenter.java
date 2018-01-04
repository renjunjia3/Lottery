package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.JCLQConfirmBetModel;
import com.quduo.lottery.ui.index.view.IJCLQConfirmBetView;

/**
 * 确认投注
 * Created by scene on 2017/12/21.
 */

public class JCLQConfirmBetPresenter extends BasePresenter<IJCLQConfirmBetView> {
    private JCLQConfirmBetModel model;

    public JCLQConfirmBetPresenter(IJCLQConfirmBetView view) {
        this.mView = view;
    }
}
