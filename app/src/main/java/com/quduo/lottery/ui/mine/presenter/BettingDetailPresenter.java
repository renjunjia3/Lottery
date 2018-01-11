package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.BettingDetailModel;
import com.quduo.lottery.ui.mine.view.IBettingDetailView;

/**
 * 投注详情
 * Created by scene on 2018/1/11.
 */

public class BettingDetailPresenter extends BasePresenter<IBettingDetailView> {
    private BettingDetailModel model;

    public BettingDetailPresenter(IBettingDetailView view) {
        this.mView = view;
        this.model = new BettingDetailModel();
    }
}
