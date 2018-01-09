package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.FC3DAwardResultModel;
import com.quduo.lottery.ui.index.view.IFC3DAwardResultView;

/**
 * 福彩3D开奖结果
 * Created by scene on 2018/1/8.
 */

public class FC3DAwardResultPresenter extends BasePresenter<IFC3DAwardResultView> {
    private FC3DAwardResultModel model;

    public FC3DAwardResultPresenter(IFC3DAwardResultView view) {
        this.mView = view;
        this.model = new FC3DAwardResultModel();
    }
}
