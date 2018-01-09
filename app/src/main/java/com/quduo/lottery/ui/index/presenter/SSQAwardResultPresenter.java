package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.SSCAwardResultModel;
import com.quduo.lottery.ui.index.model.SSQAwardResultModel;
import com.quduo.lottery.ui.index.view.ISSCAwardResultView;
import com.quduo.lottery.ui.index.view.ISSQAwardResultView;

/**
 * 双色球开奖结果
 * Created by scene on 2018/1/8.
 */

public class SSQAwardResultPresenter extends BasePresenter<ISSQAwardResultView> {
    private SSQAwardResultModel model;

    public SSQAwardResultPresenter(ISSQAwardResultView view) {
        this.mView = view;
        this.model = new SSQAwardResultModel();
    }
}
