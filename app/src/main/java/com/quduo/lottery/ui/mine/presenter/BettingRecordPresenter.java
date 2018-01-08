package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.BettingRecordModel;
import com.quduo.lottery.ui.mine.view.IBettingRecordView;

/**
 * 投注记录
 * Created by scene on 2018/1/8.
 */

public class BettingRecordPresenter extends BasePresenter<IBettingRecordView> {
    private BettingRecordModel model;

    public BettingRecordPresenter(IBettingRecordView view) {
        this.mView = view;
        this.model = new BettingRecordModel();
    }
}
