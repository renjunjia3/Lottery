package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.JCZQConfirmBetModel;
import com.quduo.lottery.ui.index.model.SSCConfirmBetModel;
import com.quduo.lottery.ui.index.view.IJCZQConfirmBetView;
import com.quduo.lottery.ui.index.view.ISSCConfirmBetView;

/**
 * 确认投注
 * Created by scene on 2017/12/21.
 */

public class JCZQConfirmBetPresenter extends BasePresenter<IJCZQConfirmBetView> {
    private JCZQConfirmBetModel model;

    public JCZQConfirmBetPresenter(IJCZQConfirmBetView view) {
        this.mView = view;
    }
}
