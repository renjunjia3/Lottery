package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.BettingRecordChildModel;
import com.quduo.lottery.ui.mine.model.ZHMXChildModel;
import com.quduo.lottery.ui.mine.view.IBettingRecordChildView;
import com.quduo.lottery.ui.mine.view.IZHMXChildView;

/**
 * 投注记录
 * Created by scene on 2018/1/5.
 */

public class BettingRecordChildPresenter extends BasePresenter<IBettingRecordChildView> {
    private BettingRecordChildModel model;

    public BettingRecordChildPresenter(IBettingRecordChildView view) {
        this.model = new BettingRecordChildModel();
        this.mView = view;
    }
}
