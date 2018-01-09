package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.FC3DAwardResultModel;
import com.quduo.lottery.ui.index.model.SSCAwardResultModel;
import com.quduo.lottery.ui.index.view.IFC3DAwardResultView;
import com.quduo.lottery.ui.index.view.ISSCAwardResultView;

/**
 * 时时彩开奖结果
 * Created by scene on 2018/1/8.
 */

public class SSCAwardResultPresenter extends BasePresenter<ISSCAwardResultView> {
    private SSCAwardResultModel model;

    public SSCAwardResultPresenter(ISSCAwardResultView view) {
        this.mView = view;
        this.model = new SSCAwardResultModel();
    }
}
