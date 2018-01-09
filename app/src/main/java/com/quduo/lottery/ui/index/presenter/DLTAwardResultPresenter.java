package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.DLTAwardResultModel;
import com.quduo.lottery.ui.index.model.SSQAwardResultModel;
import com.quduo.lottery.ui.index.view.IDLTAwardResultView;
import com.quduo.lottery.ui.index.view.ISSQAwardResultView;

/**
 * 大乐透开奖结果
 * Created by scene on 2018/1/8.
 */

public class DLTAwardResultPresenter extends BasePresenter<IDLTAwardResultView> {
    private DLTAwardResultModel model;

    public DLTAwardResultPresenter(IDLTAwardResultView view) {
        this.mView = view;
        this.model = new DLTAwardResultModel();
    }
}
